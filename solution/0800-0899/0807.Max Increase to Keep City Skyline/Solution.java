class Solution {
    public int maxIncreaseKeepingSkyline(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[] we = new int[m];
        int[] ns = new int[n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                we[i] = Math.max(we[i], grid[i][j]);
                ns[j] = Math.max(ns[j], grid[i][j]);
            }
        }
        int res = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                res += Math.min(we[i], ns[j]) - grid[i][j];
            }
        }
        return res;
    }
}