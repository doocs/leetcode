class Solution {
    public double[] cutSquares(int[] square1, int[] square2) {
        double x1 = square1[0] + square1[2] / 2.0;
        double y1 = square1[1] + square1[2] / 2.0;
        double x2 = square2[0] + square2[2] / 2.0;
        double y2 = square2[1] + square2[2] / 2.0;
        if (x1 == x2) {
            double y3 = Math.min(square1[1], square2[1]);
            double y4 = Math.max(square1[1] + square1[2], square2[1] + square2[2]);
            return new double[] {x1, y3, x2, y4};
        }
        double k = (y2 - y1) / (x2 - x1);
        double b = y1 - k * x1;
        if (Math.abs(k) > 1) {
            double y3 = Math.min(square1[1], square2[1]);
            double x3 = (y3 - b) / k;
            double y4 = Math.max(square1[1] + square1[2], square2[1] + square2[2]);
            double x4 = (y4 - b) / k;
            if (x3 > x4 || (x3 == x4 && y3 > y4)) {
                return new double[] {x4, y4, x3, y3};
            }
            return new double[] {x3, y3, x4, y4};
        } else {
            double x3 = Math.min(square1[0], square2[0]);
            double y3 = k * x3 + b;
            double x4 = Math.max(square1[0] + square1[2], square2[0] + square2[2]);
            double y4 = k * x4 + b;
            return new double[] {x3, y3, x4, y4};
        }
    }
}