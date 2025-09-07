class Solution {
    public int maxCollectedFruits(int[][] fruits) {
        int n = fruits.length;
        final int inf = 1 << 29;
        int[][] f = new int[n][n];
        for (var row : f) {
            Arrays.fill(row, -inf);
        }
        f[0][n - 1] = fruits[0][n - 1];
        for (int i = 1; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                f[i][j] = Math.max(f[i - 1][j], f[i - 1][j - 1]) + fruits[i][j];
                if (j + 1 < n) {
                    f[i][j] = Math.max(f[i][j], f[i - 1][j + 1] + fruits[i][j]);
                }
            }
        }
        f[n - 1][0] = fruits[n - 1][0];
        for (int j = 1; j < n; j++) {
            for (int i = j + 1; i < n; i++) {
                f[i][j] = Math.max(f[i][j - 1], f[i - 1][j - 1]) + fruits[i][j];
                if (i + 1 < n) {
                    f[i][j] = Math.max(f[i][j], f[i + 1][j - 1] + fruits[i][j]);
                }
            }
        }
        int ans = f[n - 2][n - 1] + f[n - 1][n - 2];
        for (int i = 0; i < n; i++) {
            ans += fruits[i][i];
        }
        return ans;
    }
}
