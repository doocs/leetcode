class Solution {
    public double[] dicesProbability(int n) {
        int[][] f = new int[n + 1][6 * n + 1];
        for (int j = 1; j <= 6; ++j) {
            f[1][j] = 1;
        }
        for (int i = 2; i <= n; ++i) {
            for (int j = i; j <= 6 * i; ++j) {
                for (int k = 1; k <= 6; ++k) {
                    if (j >= k) {
                        f[i][j] += f[i - 1][j - k];
                    }
                }
            }
        }
        double m = Math.pow(6, n);
        double[] ans = new double[5 * n + 1];
        for (int j = n; j <= 6 * n; ++j) {
            ans[j - n] = f[n][j] / m;
        }
        return ans;
    }
}