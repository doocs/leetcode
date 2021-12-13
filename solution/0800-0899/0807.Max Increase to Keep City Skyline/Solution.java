class Solution {
    public int maxIncreaseKeepingSkyline(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[] rmx = new int[m];
        int[] cmx = new int[n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                rmx[i] = Math.max(rmx[i], grid[i][j]);
                cmx[j] = Math.max(cmx[j], grid[i][j]);
            }
        }
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                ans += Math.min(rmx[i], cmx[j]) - grid[i][j];
            }
        }
        return ans;
    }
}