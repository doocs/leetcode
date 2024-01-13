class Solution {
    public int oddCells(int m, int n, int[][] indices) {
        int[][] g = new int[m][n];
        for (int[] e : indices) {
            int r = e[0], c = e[1];
            for (int i = 0; i < m; ++i) {
                g[i][c]++;
            }
            for (int j = 0; j < n; ++j) {
                g[r][j]++;
            }
        }
        int ans = 0;
        for (int[] row : g) {
            for (int v : row) {
                ans += v % 2;
            }
        }
        return ans;
    }
}