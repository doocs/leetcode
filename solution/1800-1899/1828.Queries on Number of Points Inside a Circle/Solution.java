class Solution {
    public int[] countPoints(int[][] points, int[][] queries) {
        int m = queries.length;
        int[] ans = new int[m];
        for (int k = 0; k < m; ++k) {
            int x = queries[k][0], y = queries[k][1], r = queries[k][2];
            for (var p : points) {
                int i = p[0], j = p[1];
                int dx = i - x, dy = j - y;
                if (dx * dx + dy * dy <= r * r) {
                    ++ans[k];
                }
            }
        }
        return ans;
    }
}