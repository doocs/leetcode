class Solution {
    public int minRectanglesToCoverPoints(int[][] points, int w) {
        Arrays.sort(points, (a, b) -> a[0] - b[0]);
        int ans = 0;
        int x1 = -(1 << 30);
        for (int[] p : points) {
            int x = p[0];
            if (x1 + w < x) {
                x1 = x;
                ++ans;
            }
        }
        return ans;
    }
}