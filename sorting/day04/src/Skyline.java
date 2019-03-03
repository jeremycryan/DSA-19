import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Skyline {

    static class Point {
        int x, y;
        private Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Building {
        private int l, r, h;
        Building(int l, int r, int h) {
            this.l = l;
            this.r = r;
            this.h = h;
        }
    }

    // Given an array of buildings, return a list of points representing the skyline
    //
    // Time complexity: O(NlogN), because it divides log(N) times by halving list, and iterates through points.
    public static List<Point> skyline(Building[] B) {

        ArrayList<Point> points = new ArrayList<Point>();
        if (B.length == 0) return points;

        // If there is only one building, return it's skyline points.
        if (B.length == 1) {
            Point p1 = new Point(B[0].l, B[0].h);
            Point p2 = new Point(B[0].r, 0);
            points.add(p1);
            points.add(p2);
            return points;
        }

        Building[] B1 = new Building[B.length/2];
        Building[] B2 = new Building[B.length - B.length/2];

        for (int i = 0; i < B.length; i++) {
            if (i < B.length/2) {
                B1[i] = B[i];
            } else {
                B2[i - B.length/2] = B[i];
            }
        }


        return combinePointArrays(skyline(B1), skyline(B2));
    }

    // Combines skyline arrays into a larger one.
    public static List<Point> combinePointArrays(List<Point> a, List<Point> b) {

        int i = 0;  //  Index of list a
        int j = 0;  //  Index of list b

        // Initialize variables
        ArrayList<Point> points = new ArrayList<Point>();
        Point last_a = new Point(-1, 0);;
        Point last_b = new Point(-1, 0);;
        Point a_point;
        Point b_point;
        double last_a_width = 0;
        double last_b_width = 0;

        while (true) {

            // Determine which points to fetch from arrays, and return if both arrays are over
            if (i <= a.size() - 1 && j <= b.size() - 1) {
                a_point = a.get(i);
                b_point = b.get(j);
            } else if (i <= a.size() - 1) {
                a_point = a.get(i);
                b_point = new Point(99999, 0);
            } else if (j <= b.size() - 1) {
                a_point = new Point(99999, 0);
                b_point = b.get(j);
            } else { break; }

            // Add point that is farther left
            if (a_point.x < b_point.x) {
                i++;

                // If point sticks up, add it to list
                if (canAddPoint(last_a, last_a_width, last_b, last_b_width, a_point)) {
                    points.add(a_point);
                } else if (last_a.y > last_b.y && last_b_width > a_point.x - last_b.x) {
                    Point a_point_new = new Point(a_point.x, last_b.y);
                    points.add(a_point_new);
                }

                last_a = a_point;
                if (i < a.size()) {
                    last_a_width = (a.get(i).x - a_point.x);
                } else {
                    last_a_width = 99999;
                }

            } else if (a_point.x > b_point.x) {
                j++;
                if (canAddPoint(last_a, last_a_width, last_b, last_b_width, b_point)) {
                    points.add(b_point);
                } else if (last_b.y > last_a.y && last_a_width > b_point.x - last_a.x) {
                    Point b_point_new = new Point(b_point.x, last_a.y);
                    points.add(b_point_new);
                }

                last_b = b_point;
                if (j < b.size()) {
                    last_b_width = (b.get(j).x - b_point.x);
                } else {
                    last_b_width = 99999;
                }

            } else {

                i++;
                j++;
                last_b = b_point;
                last_a = a_point;

                if (i < a.size()) {
                    last_a_width = (a.get(i).x - a_point.x);
                } else {
                    last_a_width = 99999;
                }
                if (j < b.size()) {
                    last_b_width = (b.get(j).x - b_point.x);
                } else {
                    last_b_width = 99999;
                }

                points.add(new Point(b_point.x, Math.max(b_point.y, a_point.y)));

            }

        }

        ArrayList<Point> return_points = new ArrayList<Point>();
        double last_height = 0;

        for (int k = 0; k < points.size(); k++) {

            if (points.get(k).y != last_height) {
                return_points.add(points.get(k));
                last_height = points.get(k).y;
            }

        }

        return return_points;

    }

    public static boolean canAddPoint(Point last_a_point, double last_a_width, Point last_b_point, double last_b_width, Point new_point) {

        if (new_point.y > last_a_point.y || new_point.x >= last_a_point.x + last_a_width) {
            if (new_point.y > last_b_point.y || new_point.x >= last_b_point.x + last_b_width) {
                return true;
            }
        }

        return false;

    }

}
