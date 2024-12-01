import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.util.ArrayList;
import java.util.List;

public class KdTree {
    private Node root;
    private int size;

    private static class Node {
        private Point2D point;
        private Node left, right;
        private boolean vertical;

        public Node(Point2D point, boolean vertical) {
            this.point = point;
            this.vertical = vertical;
        }
    }

    public KdTree() {
        root = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void insert(Point2D p) {
        if (p == null) throw new IllegalArgumentException("Point cannot be null");
        root = insert(root, p, true);
    }

    private Node insert(Node node, Point2D p, boolean vertical) {
        if (node == null) {
            size++;
            return new Node(p, vertical);
        }

        if (node.point.equals(p)) return node;

        int cmp = compare(p, node.point, vertical);

        if (cmp < 0) node.left = insert(node.left, p, !vertical);
        else node.right = insert(node.right, p, !vertical);

        return node;
    }

    private int compare(Point2D a, Point2D b, boolean vertical) {
        if (vertical) return Double.compare(a.x(), b.x());
        else return Double.compare(a.y(), b.y());
    }

    public boolean contains(Point2D p) {
        if (p == null) throw new IllegalArgumentException("Point cannot be null");
        return contains(root, p, true);
    }

    private boolean contains(Node node, Point2D p, boolean vertical) {
        if (node == null) return false;
        if (node.point.equals(p)) return true;

        int cmp = compare(p, node.point, vertical);

        if (cmp < 0) return contains(node.left, p, !vertical);
        else return contains(node.right, p, !vertical);
    }

    public void draw() {
        draw(root, new RectHV(0, 0, 1, 1));
    }

    private void draw(Node node, RectHV rect) {
        if (node == null) return;

        // Draw the point
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.01);
        node.point.draw();

        // Draw the splitting line
        StdDraw.setPenRadius();
        if (node.vertical) {
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.line(node.point.x(), rect.ymin(), node.point.x(), rect.ymax());
        } else {
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.line(rect.xmin(), node.point.y(), rect.xmax(), node.point.y());
        }

        // Recursively draw children
        RectHV leftRect, rightRect;
        if (node.vertical) {
            leftRect = new RectHV(rect.xmin(), rect.ymin(), node.point.x(), rect.ymax());
            rightRect = new RectHV(node.point.x(), rect.ymin(), rect.xmax(), rect.ymax());
        } else {
            leftRect = new RectHV(rect.xmin(), rect.ymin(), rect.xmax(), node.point.y());
            rightRect = new RectHV(rect.xmin(), node.point.y(), rect.xmax(), rect.ymax());
        }

        draw(node.left, leftRect);
        draw(node.right, rightRect);
    }

    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new IllegalArgumentException("Rectangle cannot be null");
        List<Point2D> pointsInRange = new ArrayList<>();
        range(root, rect, pointsInRange, new RectHV(0, 0, 1, 1));
        return pointsInRange;
    }

    private void range(Node node, RectHV queryRect, List<Point2D> pointsInRange, RectHV nodeRect) {
        if (node == null) return;

        if (queryRect.intersects(nodeRect)) {
            if (queryRect.contains(node.point)) {
                pointsInRange.add(node.point);
            }

            RectHV leftRect, rightRect;
            if (node.vertical) {
                leftRect = new RectHV(nodeRect.xmin(), nodeRect.ymin(), node.point.x(), nodeRect.ymax());
                rightRect = new RectHV(node.point.x(), nodeRect.ymin(), nodeRect.xmax(), nodeRect.ymax());
            } else {
                leftRect = new RectHV(nodeRect.xmin(), nodeRect.ymin(), nodeRect.xmax(), node.point.y());
                rightRect = new RectHV(nodeRect.xmin(), node.point.y(), nodeRect.xmax(), nodeRect.ymax());
            }

            range(node.left, queryRect, pointsInRange, leftRect);
            range(node.right, queryRect, pointsInRange, rightRect);
        }
    }

    public Point2D nearest(Point2D p) {
        if (p == null) throw new IllegalArgumentException("Point cannot be null");
        if (isEmpty()) return null;
        return nearest(root, p, root.point, new RectHV(0, 0, 1, 1));
    }

    private Point2D nearest(Node node, Point2D query, Point2D champion, RectHV rect) {
        if (node == null) return champion;

        double distToChampion = query.distanceSquaredTo(champion);
        if (rect.distanceSquaredTo(query) >= distToChampion) {
            return champion;
        }

        if (query.distanceSquaredTo(node.point) < distToChampion) {
            champion = node.point;
        }

        RectHV leftRect, rightRect;
        if (node.vertical) {
            leftRect = new RectHV(rect.xmin(), rect.ymin(), node.point.x(), rect.ymax());
            rightRect = new RectHV(node.point.x(), rect.ymin(), rect.xmax(), rect.ymax());
        } else {
            leftRect = new RectHV(rect.xmin(), rect.ymin(), rect.xmax(), node.point.y());
            rightRect = new RectHV(rect.xmin(), node.point.y(), rect.xmax(), rect.ymax());
        }

        if ((node.vertical && query.x() < node.point.x()) || (!node.vertical && query.y() < node.point.y())) {
            champion = nearest(node.left, query, champion, leftRect);
            champion = nearest(node.right, query, champion, rightRect);
        } else {
            champion = nearest(node.right, query, champion, rightRect);
            champion = nearest(node.left, query, champion, leftRect);
        }

        return champion;
    }

    public static void main(String[] args) {
        // Add your unit tests here
	KdTree kdtree = new KdTree();

// Insert points
kdtree.insert(new Point2D(0.7, 0.2));
kdtree.insert(new Point2D(0.5, 0.4));
kdtree.insert(new Point2D(0.2, 0.3));

// Check if a point is in the tree
boolean contains = kdtree.contains(new Point2D(0.5, 0.4));

// Find points in a range
Iterable<Point2D> pointsInRange = kdtree.range(new RectHV(0.0, 0.0, 0.5, 0.5));

// Find nearest neighbor
Point2D nearest = kdtree.nearest(new Point2D(0.1, 0.1));

// Draw the tree
kdtree.draw();
    }
}
