import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
public class BruteCollinearPoints
{	
	private final java.util.ArrayList<LineSegment> segments = new java.util.ArrayList<>(); 
	private ArrayList<Point> list = new ArrayList<>();
	public BruteCollinearPoints(Point[] points)
	{
		if (points == null) throw new IllegalArgumentException("Argument is null");
        	for (Point point : points) {
            		if (point == null) throw new IllegalArgumentException("Point is null");
			if (!list.contains(point)) list.add(point);
			else throw new IllegalArgumentException("Duplicates");
       		}
		
		for(int p=0;p<points.length-3;p++)
			for(int q=p+1;q<points.length-2;q++)
				for(int r=q+1;r<points.length-1;r++)
					for(int s=r+1;s<points.length;s++)
					{
						Point pp = points[p];
						Point qq = points[q];
						Point rr = points[r];
						Point ss = points[s];
						if(pp.slopeTo(qq)==pp.slopeTo(rr)
							&&pp.slopeTo(rr)==pp.slopeTo(ss))
						{
							List<Point> collinearPoints = new ArrayList<>();
							collinearPoints.add(pp);
							collinearPoints.add(qq);
							collinearPoints.add(rr);
							collinearPoints.add(ss);
							Collections.sort(collinearPoints);	
							segments.add(new LineSegment(collinearPoints.get(0),collinearPoints.get(3)));
						}
							
					}
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
    StdDraw.enableDoubleBuffering();
    StdDraw.setXscale(0, 32768);
    StdDraw.setYscale(0, 32768);
    for (Point p : points) {
        p.draw();
    }
    StdDraw.show();

    // print and draw the line segments
    BruteCollinearPoints collinear = new BruteCollinearPoints(points);
    for (LineSegment segment : collinear.segments()) {
        StdOut.println(segment);
        segment.draw();
    }
    StdDraw.show();
}		

}
