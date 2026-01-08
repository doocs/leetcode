class Solution {
    private int[][] squares;
    private double s;

    private boolean check(double y1) {
        double t = 0.0;
        for (int[] a : squares) {
            int y = a[1];
            int l = a[2];
            if (y < y1) {
                t += (double) l * Math.min(y1 - y, l);
            }
        }
        return t >= s / 2.0;
    }

    public double separateSquares(int[][] squares) {
        this.squares = squares;
        s = 0.0;
        double l = 0.0;
        double r = 0.0;
        for (int[] a : squares) {
            s += (double) a[2] * a[2];
            r = Math.max(r, a[1] + a[2]);
        }

        double eps = 1e-5;
        while (r - l > eps) {
            double mid = (l + r) / 2.0;
            if (check(mid)) {
                r = mid;
            } else {
                l = mid;
            }
        }
        return r;
    }
}
