class Solution {
    public int[][] constructProductMatrix(int[][] grid) {
        final int mod = 12345;
        int n = grid.length, m = grid[0].length;
        int[][] p = new int[n][m];
        long suf = 1;
        for (int i = n - 1; i >= 0; --i) {
            for (int j = m - 1; j >= 0; --j) {
                p[i][j] = (int) suf;
                suf = suf * grid[i][j] % mod;
            }
        }
        long pre = 1;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                p[i][j] = (int) (p[i][j] * pre % mod);
                pre = pre * grid[i][j] % mod;
            }
        }
        return p;
    }
}