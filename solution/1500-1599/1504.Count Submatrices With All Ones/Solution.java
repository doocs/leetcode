class Solution {
    public int numSubmat(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int[][] g = new int[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (mat[i][j] == 1) {
                    g[i][j] = j == 0 ? 1 : 1 + g[i][j - 1];
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                int col = 1 << 30;
                for (int k = i; k >= 0 && col > 0; --k) {
                    col = Math.min(col, g[k][j]);
                    ans += col;
                }
            }
        }
        return ans;
    }
}