import java.util.*;

public class ClosestPair {

    public static class Point {
        double x, y;
        public Point(double x, double y) { this.x = x; this.y = y; }
    }

    public static double closestPair(Point[] points) {
        Arrays.sort(points, Comparator.comparingDouble(p -> p.x));
        return closestPair(points, 0, points.length - 1, new Point[points.length]);
    }

    private static double closestPair(Point[] pts, int left, int right, Point[] buffer) {
        if (right - left <= 3) {
            double min = Double.POSITIVE_INFINITY;
            for (int i = left; i <= right; i++) {
                for (int j = i + 1; j <= right; j++) {
                    min = Math.min(min, dist(pts[i], pts[j]));
                }
            }
            Arrays.sort(pts, left, right + 1, Comparator.comparingDouble(p -> p.y));
            return min;
        }

        int mid = (left + right) / 2;
        double midX = pts[mid].x;
        double d1 = closestPair(pts, left, mid, buffer);
        double d2 = closestPair(pts, mid + 1, right, buffer);
        double d = Math.min(d1, d2);

        mergeByY(pts, left, mid, right, buffer);

        int m = 0;
        for (int i = left; i <= right; i++) {
            if (Math.abs(pts[i].x - midX) < d) buffer[m++] = pts[i];
        }

        for (int i = 0; i < m; i++) {
            for (int j = i + 1; j < m && (buffer[j].y - buffer[i].y) < d; j++) {
                d = Math.min(d, dist(buffer[i], buffer[j]));
            }
        }
        return d;
    }

    private static void mergeByY(Point[] pts, int left, int mid, int right, Point[] buffer) {
        int i = left, j = mid + 1, k = 0;
        while (i <= mid && j <= right) {
            if (pts[i].y <= pts[j].y) buffer[k++] = pts[i++];
            else buffer[k++] = pts[j++];
        }
        while (i <= mid) buffer[k++] = pts[i++];
        while (j <= right) buffer[k++] = pts[j++];
        System.arraycopy(buffer, 0, pts, left, k);
    }

    private static double dist(Point a, Point b) {
        double dx = a.x - b.x, dy = a.y - b.y;
        return Math.sqrt(dx * dx + dy * dy);
    }
}
