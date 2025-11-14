package edu.hw8;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class B08_04 {

    public static class Point {
        private final double x;
        private final double y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public double getX() {
            return x;
        }

        public double getY() {
            return y;
        }

        public double distanceSquaredFromOrigin() {
            return x * x + y * y;
        }

        @Override
        public String toString() {
            return "(" + x + ", " + y + ')';
        }
    }

    public static List<Point> sortByDistanceFromOrigin(Collection<Point> points) {
        PriorityQueue<Point> queue =
                new PriorityQueue<>(Comparator.comparingDouble(Point::distanceSquaredFromOrigin));

        queue.addAll(points);

        List<Point> result = new ArrayList<>(points.size());
        while (!queue.isEmpty()) {
            result.add(queue.poll());
        }
        return result;
    }

    public static void main(String[] args) {
        List<Point> points = new ArrayList<>();
        points.add(new Point(1, 1));
        points.add(new Point(3, 4));
        points.add(new Point(0, 2));
        points.add(new Point(-1, -1));

        List<Point> sorted = sortByDistanceFromOrigin(points);
        for (Point p : sorted) {
            System.out.println(p + " | distance^2 = " + p.distanceSquaredFromOrigin());
        }
    }
}
