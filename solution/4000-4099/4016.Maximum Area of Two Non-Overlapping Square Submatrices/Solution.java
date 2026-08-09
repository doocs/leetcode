class Solution {
    public int maxArea(int[][] mat) {
        return Math.max(calc(mat), calc(transpose(mat)));
    }

    private int calc(int[][] mat) {
        int m = mat.length, n = mat[0].length;

        int[][] f = new int[m + 1][n + 1];
        int[] g = new int[m + 1];
        int[] suf = new int[m + 1];

        for (int i = m - 1; i > 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (mat[i][j] != 0) {
                    f[i][j] = Math.min(Math.min(f[i + 1][j], f[i][j + 1]), f[i + 1][j + 1]) + 1;
                    g[i] = Math.max(g[i], f[i][j]);
                }
            }
            suf[i] = Math.max(suf[i + 1], g[i]);
        }

        f = new int[m + 1][n + 1];
        g = new int[m + 1];
        int[] pre = new int[m + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (mat[i - 1][j - 1] != 0) {
                    f[i][j] = Math.min(Math.min(f[i - 1][j], f[i][j - 1]), f[i - 1][j - 1]) + 1;
                    g[i] = Math.max(g[i], f[i][j]);
                }
            }
            pre[i] = Math.max(pre[i - 1], g[i]);
        }

        int ans = 0;
        for (int i = 1; i < m; i++) {
            int t = Math.min(pre[i], suf[i]);
            ans = Math.max(ans, t * t);
        }
        return ans;
    }

    private int[][] transpose(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int[][] ans = new int[n][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                ans[j][i] = mat[i][j];
            }
        }
        return ans;
    }
}