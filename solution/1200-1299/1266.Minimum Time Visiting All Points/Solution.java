class Solution {
    public int minTimeToVisitAllPoints(int[][] points) {
        int res = 0;
        for (int i = 1; i < points.length; ++i) {
            int x0 = points[i - 1][0], y0 = points[i - 1][1];
            int x1 = points[i][0], y1 = points[i][1];
            res += Math.max(Math.abs(x0 - x1), Math.abs(y0 - y1));
        }
        return res;
    }
}