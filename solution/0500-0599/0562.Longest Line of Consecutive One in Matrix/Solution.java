class Solution {
    public int longestLine(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int[][] a = new int[m + 2][n + 2];
        int[][] b = new int[m + 2][n + 2];
        int[][] c = new int[m + 2][n + 2];
        int[][] d = new int[m + 2][n + 2];
        int ans = 0;
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (mat[i - 1][j - 1] == 1) {
                    a[i][j] = a[i - 1][j] + 1;
                    b[i][j] = b[i][j - 1] + 1;
                    c[i][j] = c[i - 1][j - 1] + 1;
                    d[i][j] = d[i - 1][j + 1] + 1;
                    ans = max(ans, a[i][j], b[i][j], c[i][j], d[i][j]);
                }
            }
        }
        return ans;
    }

    private int max(int... arr) {
        int ans = 0;
        for (int v : arr) {
            ans = Math.max(ans, v);
        }
        return ans;
    }
}