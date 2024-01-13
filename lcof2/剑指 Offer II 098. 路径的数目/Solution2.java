class Solution {
    public int uniquePaths(int m, int n) {
        var f = new int[m][n];
        for (var g : f) {
            Arrays.fill(g, 1);
        }
        for (int i = 1; i < m; ++i) {
            for (int j = 1; j < n; j++) {
                f[i][j] = f[i - 1][j] + f[i][j - 1];
            }
        }
        return f[m - 1][n - 1];
    }
}