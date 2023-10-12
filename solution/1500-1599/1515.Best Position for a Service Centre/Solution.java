class Solution {
    public double getMinDistSum(int[][] positions) {
        int n = positions.length;
        double x = 0, y = 0;
        for (int[] p : positions) {
            x += p[0];
            y += p[1];
        }
        x /= n;
        y /= n;
        double decay = 0.999;
        double eps = 1e-6;
        double alpha = 0.5;
        while (true) {
            double gradX = 0, gradY = 0;
            double dist = 0;
            for (int[] p : positions) {
                double a = x - p[0], b = y - p[1];
                double c = Math.sqrt(a * a + b * b);
                gradX += a / (c + 1e-8);
                gradY += b / (c + 1e-8);
                dist += c;
            }
            double dx = gradX * alpha, dy = gradY * alpha;
            if (Math.abs(dx) <= eps && Math.abs(dy) <= eps) {
                return dist;
            }
            x -= dx;
            y -= dy;
            alpha *= decay;
        }
    }
}