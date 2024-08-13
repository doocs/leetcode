class Solution {
    public int numPoints(int[][] darts, int r) {
        int n = darts.length;
        int maxDarts = 1;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                List<double[]> centers
                    = possibleCenters(darts[i][0], darts[i][1], darts[j][0], darts[j][1], r);
                for (double[] center : centers) {
                    maxDarts = Math.max(maxDarts, countDarts(center[0], center[1], darts, r));
                }
            }
        }
        return maxDarts;
    }

    private List<double[]> possibleCenters(int x1, int y1, int x2, int y2, int r) {
        List<double[]> centers = new ArrayList<>();
        double dx = x2 - x1;
        double dy = y2 - y1;
        double d = Math.sqrt(dx * dx + dy * dy);
        if (d > 2 * r) {
            return centers;
        }
        double midX = (x1 + x2) / 2.0;
        double midY = (y1 + y2) / 2.0;
        double distToCenter = Math.sqrt(r * r - (d / 2.0) * (d / 2.0));
        double offsetX = distToCenter * dy / d;
        double offsetY = distToCenter * -dx / d;

        centers.add(new double[] {midX + offsetX, midY + offsetY});
        centers.add(new double[] {midX - offsetX, midY - offsetY});
        return centers;
    }

    private int countDarts(double x, double y, int[][] darts, int r) {
        int count = 0;
        for (int[] dart : darts) {
            if (Math.sqrt(Math.pow(dart[0] - x, 2) + Math.pow(dart[1] - y, 2)) <= r + 1e-7) {
                count++;
            }
        }
        return count;
    }
}
