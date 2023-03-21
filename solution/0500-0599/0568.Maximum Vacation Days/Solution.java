class Solution {
    public int maxVacationDays(int[][] flights, int[][] days) {
        int n = flights.length;
        int K = days[0].length;
        final int inf = 1 << 30;
        int[][] f = new int[K + 1][n];
        for (var g : f) {
            Arrays.fill(g, -inf);
        }
        f[0][0] = 0;
        for (int k = 1; k <= K; ++k) {
            for (int j = 0; j < n; ++j) {
                f[k][j] = f[k - 1][j];
                for (int i = 0; i < n; ++i) {
                    if (flights[i][j] == 1) {
                        f[k][j] = Math.max(f[k][j], f[k - 1][i]);
                    }
                }
                f[k][j] += days[j][k - 1];
            }
        }
        int ans = 0;
        for (int j = 0; j < n; ++j) {
            ans = Math.max(ans, f[K][j]);
        }
        return ans;
    }
}