class Solution {
    public int uniquePaths(int m, int n) {
        int[][] res = new int[n][m];
        for (int i = 0; i < m; ++i) {
            res[0][i] = 1;
        }
        for (int i = 1; i < n; ++i) {
            res[i][0] = 1;
        }
        for (int i = 1; i < n; ++i) {
            for (int j = 1; j < m; ++j) {
                res[i][j] = res[i - 1][j] + res[i][j - 1];
            }
        }
        return res[n - 1][m - 1];
    }
}