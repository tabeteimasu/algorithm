/******************************************************************************
 *  Compilation:  javac Point.java
 *  Execution:    java Point
 *  Dependencies: none
 *  
 *  An immutable data type for points in the plane.
 *  For use on Coursera, Algorithms Part I programming assignment.
 *
 ******************************************************************************/

import java.util.Comparator;
import edu.princeton.cs.algs4.StdDraw;

public class Point implements Comparable<Point> {

    private final int x;     // x-coordinate of this point
    private final int y;     // y-coordinate of this point

    /**
     * Initializes a new point.
     *
     * @param  x the <em>x</em>-coordinate of the point
     * @param  y the <em>y</em>-coordinate of the point
     */
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    /**
     * Draws this point to standard draw.
     */
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    /**
     * Draws the line segment between this point and the specified point
     * to standard draw.
     *
     * @param that the other point
     */
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    /**
     * Returns the slope between this point and the specified point.
     * Formally, if the two points are (x0, y0) and (x1, y1), then the slope
     * is (y1 - y0) / (x1 - x0). For completeness, the slope is defined to be
     * +0.0 if the line segment connecting the two points is horizontal;
     * Double.POSITIVE_INFINITY if the line segment is vertical;
     * and Double.NEGATIVE_INFINITY if (x0, y0) and (x1, y1) are equal.
     *
     * @param  that the other point
     * @return the slope between this point and the specified point
     */
    public double slopeTo(Point that) {
        if(this.x-that.x==0&&this.y-that.y==0) return Double.NEGATIVE_INFINITY;//itself
	if(this.x-that.x==0)	return Double.POSITIVE_INFINITY;//vertical
	if(this.y-that.y==0)	return 0;//horizontal
	return (double)(that.y-this.y)/(double)(that.x-this.x);
    }

    /**
     * Compares two points by y-coordinate, breaking ties by x-coordinate.
     * Formally, the invoking point (x0, y0) is less than the argument point
     * (x1, y1) if and only if either y0 < y1 or if y0 = y1 and x0 < x1.
     *
     * @param  that the other point
     * @return the value <tt>0</tt> if this point is equal to the argument
     *         point (x0 = x1 and y0 = y1);
     *         a negative integer if this point is less than the argument
     *         point; and a positive integer if this point is greater than the
     *         argument point
     */
    public int compareTo(Point that) {
        if(this.y<that.y) return -1;
	else if(this.y>that.y) return 1;
	else
	{
		if(this.x<that.x) return -1;
		else if(this.x>that.x) return 1;
		else return 0;
	}
    }

    /**
     * Compares two points by the slope they make with this point.
     * The slope is defined as in the slopeTo() method.
     *
     * @return the Comparator that defines this ordering on points
     */
    public Comparator<Point> slopeOrder() {
        return new Comparator<>()
	{
		@Override
		public int compare(Point a,Point b)
		{
			if(slopeTo(a)<slopeTo(b)) return -1;
			else if(slopeTo(a)>slopeTo(b)) return 1;
			else return a.compareTo(b);
		}	
	};
    }



    /**
     * Returns a string representation of this point.
     * This method is provide for debugging;
     * your program should not rely on the format of the string representation.
     *
     * @return a string representation of this point
     */
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

	/*    
    public static void main(String[] args) {
        Point a = new Point(0,0);
	Point b = new Point(1,1);
	Point c = new Point(5,5);
	Point d = new Point(3,3);
	Point e = new Point(7,7);
	Point f = new Point(6,6);
	Point g = new Point(-2,-2);
	Point h = new Point(-3,-3);
	java.util.List<Point> list = new java.util.ArrayList<>();
	list.add(a);
	list.add(b);
	list.add(c);
	list.add(d);
	list.add(e);
	list.add(f);
	list.add(g);
	list.add(h);
	list.sort(h.slopeOrder());
	for(Point p:list)
		System.out.println(p);
	Point one = new Point(4,4);
	Point two = new Point(2,2);
	Point three = new Point(2,1);
	System.out.println(one.slopeTo(two)+" "+one.slopeTo(three)); 
    }
	*/
}
