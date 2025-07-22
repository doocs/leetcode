class Solution {
    public int minRectanglesToCoverPoints(int[][] points, int w) {
        Arrays.sort(points, (a, b) -> a[0] - b[0]);
        int ans = 0, x1 = -1;
        for (int[] p : points) {
            int x = p[0];
            if (x > x1) {
                ++ans;
                x1 = x + w;
            }
        }
        return ans;
    }
}
