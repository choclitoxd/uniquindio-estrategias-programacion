package AlgoritmosVoraces;

import java.util.*;
/**
    * Smaller Elements Count - Divide y Vencerás
    * Basado en el enfoque de GeeksForGeeks:
    * https://www.geeksforgeeks.org/dsa/majority-element/
*/
public class ConvexHull {
   // Class to represent a point with x and y coordinates
    static class Point {
        double x, y;

        // Constructor to initialize point
        Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        // Override equals to compare two points
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Point t = (Point) obj;
            return Double.compare(t.x, x) == 0 && Double.compare(t.y, y) == 0;
        }
    }

    // Function to calculate orientation of ordered triplet (a, b, c)
    static int orientation(Point a, Point b, Point c) {
        // Compute the cross product value
        double v = a.x * (b.y - c.y) + 
                   b.x * (c.y - a.y) + 
                   c.x * (a.y - b.y);

        // Return -1 for clockwise, 1 for counter-clockwise, 0 for collinear
        if (v < 0) return -1;
        if (v > 0) return 1;
        return 0;
    }

    // Function to compute square of distance between two points
    static double distSq(Point a, Point b) {
        return (a.x - b.x) * (a.x - b.x) + 
               (a.y - b.y) * (a.y - b.y);
    }

    // Function to find the convex hull of a set of points
    static int[][] findConvexHull(int[][] points) {
        // Get number of points points
        int n = points.length;

        // Convex hull is not possible with less than 3 points
        if (n < 3) return new int[][]{{-1}};

        // Convert int[][] points to list of Point objects
        ArrayList<Point> a = new ArrayList<>();
        for (int[] p : points) {
            a.add(new Point(p[0], p[1]));
        }

        // Find the bottom-most point (and left-most if tie)
        Point p0 = Collections.min(a, (p1, p2) -> {
            if (p1.y != p2.y)
                return Double.compare(p1.y, p2.y);
            return Double.compare(p1.x, p2.x);
        });

        // Sort points based on polar angle with respect to p0
        a.sort((p1, p2) -> {
            int o = orientation(p0, p1, p2);

            // If collinear, sort by distance from p0
            if (o == 0) {
                return Double.compare(distSq(p0, p1), distSq(p0, p2));
            }

            // Otherwise sort by orientation
            return (o < 0) ? -1 : 1;
        });

        // Stack to store the points of convex hull
        Stack<Point> st = new Stack<>();

        // Traverse sorted points
        for (Point p : a) {

            // Remove last point while the angle formed is not counter-clockwise
            while (st.size() > 1 && orientation(st.get(st.size() - 2), st.peek(), p) >= 0)
                st.pop();

            // Add current point to the convex hull
            st.push(p);
        }

        // If convex hull has less than 3 points, it's invalid
        if (st.size() < 3) return new int[][]{{-1}};

        // Convert the convex hull points into int[][]
        int[][] result = new int[st.size()][2];
        int i = 0;
        for (Point p : st) {
            result[i][0] = (int)p.x;
            result[i][1] = (int)p.y;
            i++;
        }

        return result;
    }

    public static void main(String[] args) {

        // points set of points
        int[][] points = {
            {0, 0}, {1, -4}, {-1, -5}, {-5, -3}, {-3, -1},
            {-1, -3}, {-2, -2}, {-1, -1}, {-2, -1}, {-1, 1}
        };
        
        // Call function to get convex hull
        int[][] hull = findConvexHull(points);

        // Print result
        if (hull.length == 1 && hull[0].length == 1) {
            System.out.println(hull[0][0]);
        } else {
            for (int[] point : hull) {
                System.out.println(point[0] + ", " + point[1]);
            }
        }
    } 
}
