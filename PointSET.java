import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.StdDraw;

import java.util.ArrayList;
import java.util.List;

public class PointSET {
    private final SET<Point2D> points;

    // construct an empty set of points
    public PointSET() {
        points = new SET<>();
    }

    // is the set empty?
    public boolean isEmpty() {
        return points.isEmpty();
    }

    // number of points in the set
    public int size() {
        return points.size();
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        if (p == null) throw new IllegalArgumentException("Point cannot be null");
        points.add(p);
    }

    // does the set contain point p?
    public boolean contains(Point2D p) {
        if (p == null) throw new IllegalArgumentException("Point cannot be null");
        return points.contains(p);
    }

    // draw all points to standard draw
    public void draw() {
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.01);
        for (Point2D p : points) {
            p.draw();
        }
    }

    // all points that are inside the rectangle (or on the boundary)
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new IllegalArgumentException("Rectangle cannot be null");
        List<Point2D> pointsInRange = new ArrayList<>();
        for (Point2D p : points) {
            if (rect.contains(p)) {
                pointsInRange.add(p);
            }
        }
        return pointsInRange;
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        if (p == null) throw new IllegalArgumentException("Point cannot be null");
        if (isEmpty()) return null;

        Point2D nearest = null;
        double minDistance = Double.POSITIVE_INFINITY;

        for (Point2D point : points) {
            double distance = p.distanceSquaredTo(point);
            if (distance < minDistance) {
                minDistance = distance;
                nearest = point;
            }
        }

        return nearest;
    }

    // unit testing of the methods (optional)
    public static void main(String[] args) {
        // Add your unit tests here
	 PointSET pointSet = new PointSET();
        // Example usage
        pointSet.insert(new Point2D(0.1, 0.2));
        pointSet.insert(new Point2D(0.3, 0.4));
        System.out.println("Set is empty: " + pointSet.isEmpty());
        System.out.println("Set size: " + pointSet.size());
        System.out.println("Contains (0.1, 0.2): " + pointSet.contains(new Point2D(0.1, 0.2)));
    }
}
