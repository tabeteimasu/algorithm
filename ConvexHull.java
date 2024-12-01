import java.util.Arrays;
import java.util.Stack;
import java.util.Comparator;
public class Point
{
	private final double x;
	private final double y;
	
	public Point(double x,double y)
	{
		this.x = x;
		this.y = y;
	}

	public static int ccw(Point a,Point b, Point c)
	{
		double area2 = (b.x-a.x)*(c.y-a.y) - (b.y-a.y)*(c.x-a.x);
		if(area2<0)	return -1; //clockwise
		else if(area2>0)	return +1; //counter-clockwise
		else		return  0; //collinear
	}
	
	public static double polarAngle(Point a,Point b)
	{
		double deltaX = b.x - a.x;
		double deltaY = b.y - a.y;

		double thetaRadians = Math.atan2(deltaY,deltaX);
		return thetaRadians;
	}
	
	public static double distance(Point a,Point b)
	{
		return Math.hypot(b.x-a.x,b.y-a.y);
	}

	public static int intersection(Point[] a,Point[] b)
	{
		Arrays.sort(a,Y_ORDER());
		Arrays.sort(a,POLAR_ANGLE_ORDER(a[0]));
		System.out.println("-------order by angle----------");
                for(Point point:a)
           		System.out.println(point);
                System.out.println("----------------------------");
		int count = 0;
		for(Point p:b)
		{
			if(binarySearch(a,p)==0)
			{
				count++;
				System.out.println("Found intersection:"+p);
			}
		}
		return count;
	}

	public static int binarySearch(Point[] a,Point target)
	{
		int low = 0;
		int high = a.length-1;
		while(low<=high)
		{
			int mid = low + (high-low)/2;
			double angleMid = (polarAngle(a[mid],a[0]));
			double angleTarget = polarAngle(target,a[0]);
			if(angleMid>angleTarget) high=mid-1;
			else if(angleMid<angleTarget) low=mid+1;
			else 
			{
				double distanceMid = distance(a[mid],a[0]);
				double distanceTarget = distance(target,a[0]);
				if(distanceMid>distanceTarget) high=mid-1;
				else if(distanceMid<distanceTarget) low=mid+1;
				else return 0;
			}
		}
		if(target.x==a[0].x&&target.y==a[0].y) return 0;
		return -1;
	}	

	public static Comparator<Point> Y_ORDER()
	{
		return new Comparator<Point>()
		{
			@Override
			public int compare(Point p1,Point p2)
			{
				return Double.compare(p1.y,p2.y);
			}
		};
	}
	
	public static Comparator<Point> POLAR_ANGLE_ORDER(final Point origin)
	{
		return new Comparator<Point>()
		{
			@Override
			public int compare(Point p1,Point p2)
			{
				double angle1 = polarAngle(origin,p1);
				double angle2 = polarAngle(origin,p2);
				return Double.compare(angle1,angle2);
			}
		};
	}
	
	public static void main(String args[])
	{
		Point a = new Point(2,0);
		Point b = new Point(0,2);
		Point c = new Point(-2,0);
		System.out.println(ccw(a,b,c));

		Point x = new Point(-3,0);
		Point y = new Point(0,3);
		Point z = new Point(3,0);
		System.out.println(ccw(x,y,z));
	
		Point f = new Point(0,0);
		Point g = new Point(1,0);
		Point h = new Point(2,0);
		System.out.println(ccw(f,g,h));

		System.out.println(polarAngle(x,y));

		Point[] p = {a,b,c,x,y,z,new Point(2,-2)};
		Stack<Point> hull = new Stack<>();
		Arrays.sort(p,Y_ORDER());
		/*
		System.out.println("-------order by y----------");
		for(Point point:p)
			System.out.println(point);
		System.out.println("----------------------------");
		*/
		Arrays.sort(p,POLAR_ANGLE_ORDER(p[0]));
		System.out.println("-------order by angle----------");
                for(Point point:p)
                        System.out.println(point);
                System.out.println("----------------------------");
		hull.push(p[0]);
		hull.push(p[1]);
		for(int i=2;i<p.length;i++)
		{
			Point top = hull.pop();
			while(ccw(hull.peek(),top,p[i])<=0)
				top = hull.pop();
			hull.push(top);
			hull.push(p[i]);
		}
		for(Point point:hull)
			System.out.println(point);

		Point[] array1 = {a,b,c,x,y};
		Point[] array2 = {a,c,f,g,x};
		intersection(array1,array2);
	}

	public String toString()
	{
		return "("+x+","+y+")";
	}
}
