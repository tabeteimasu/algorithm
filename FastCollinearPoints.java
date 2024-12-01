import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
public class FastCollinearPoints
{
	private java.util.List<Point> collinearPoints = new java.util.ArrayList<>();
	private java.util.List<LineSegment> segments = new java.util.ArrayList<>();
	private java.util.List<Point> list = new java.util.ArrayList<>();
	public FastCollinearPoints(Point[] points)
	{		
		if (points == null) throw new IllegalArgumentException("Argument is null");
        	for (Point point : points) {
            		if (point == null) throw new IllegalArgumentException("Point is null");
			if (!list.contains(point)) list.add(point);
			else throw new IllegalArgumentException("Duplicates!");
       		}
		java.util.List<Point> pointsList = new java.util.ArrayList<>();
		for(int k=0;k<points.length;k++) pointsList.add(points[k]);
		for(Point p:points)
		{
			
			pointsList.sort(p.slopeOrder());
			/*
			System.out.println("------------------");
			System.out.println("sorted point list by slope:");
			System.out.println(pointsList);
			*/
			for(int i=1;i<points.length-1;i++)
			{
				if(p.slopeTo(pointsList.get(i))==p.slopeTo(pointsList.get(i+1)))
				{
					/*
					System.out.println("same slope");
					System.out.println("orgin->point i:point i+1");
					System.out.println(p+"->"+pointsList.get(i)+pointsList.get(i+1));
					System.out.println("slope1:"+p.slopeTo(pointsList.get(i)));
					System.out.println("slope2:"+p.slopeTo(pointsList.get(i+1)));	
					*/
					if(collinearPoints.isEmpty())
					{
					collinearPoints.add(p);
					collinearPoints.add(pointsList.get(i));
					collinearPoints.add(pointsList.get(i+1));
					}
					else
					{
					collinearPoints.add(pointsList.get(i+1));
					}
				}else{
					/*
					System.out.println("different slope");
					System.out.println("orgin->point i:point i+1");
					System.out.println(p+"->"+pointsList.get(i)+pointsList.get(i+1));
					System.out.println("slope1:"+p.slopeTo(pointsList.get(i)));
					System.out.println("slope2:"+p.slopeTo(pointsList.get(i+1)));
					if(collinearPoints.size()>=3)
					{
						addSegmentIfNew(collinearPoints);
						System.out.println("line segment added");
					}
					collinearPoints.clear();
					*/
				}
			}
			if(collinearPoints.size()>=3) addSegmentIfNew(collinearPoints);//in case every point is collinear
			collinearPoints.clear();
		}
		
	}

	private void addSegmentIfNew(java.util.List<Point> points)
	{
		Point[] segmentPoints = points.toArray(new Point[points.size()]);
		java.util.Arrays.sort(segmentPoints);
		//System.out.println(java.util.Arrays.toString(segmentPoints));
		LineSegment segment = new LineSegment(segmentPoints[0],segmentPoints[segmentPoints.length-1]);
		//System.out.println(segmentPoints[0]+"->"+segmentPoints[segmentPoints.length-1]);
		for(LineSegment existingSegment:segments)
			if(existingSegment.toString().equals(segment.toString())) return;
		segments.add(segment);
	}

	public int numberOfSegments()
	{
		return segments.size();
	}

	public LineSegment[] segments()
	{
		return segments.toArray(new LineSegment[0]);
	}

	public static void main(String[] args) {

    // read the n points from a file
    In in = new In(args[0]);
    int n = in.readInt();
    Point[] points = new Point[n];
    for (int i = 0; i < n; i++) {
        int x = in.readInt();
        int y = in.readInt();
        points[i] = new Point(x, y);
    }

    // draw the points
    //StdDraw.enableDoubleBuffering();
    //StdDraw.setXscale(0, 32768);
    //StdDraw.setYscale(0, 32768);
    //for (Point p : points) {
    //    p.draw();
    //}
    //StdDraw.show();

    // print and draw the line segments
    FastCollinearPoints collinear = new FastCollinearPoints(points);
    StdOut.println("-----line segments--------");
    for (LineSegment segment : collinear.segments()) {
        StdOut.println(segment);
        //segment.draw();
    }
    //StdDraw.show();
}

}
