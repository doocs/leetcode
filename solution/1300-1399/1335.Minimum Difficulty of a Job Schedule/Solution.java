class Solution {
    public int minDifficulty(int[] jobDifficulty, int d) {
        int n = jobDifficulty.length;
        final int inf = 1 << 30;
        int[][] f = new int[n + 1][d + 1];
        for (int[] g : f) {
            Arrays.fill(g, inf);
        }
        f[0][0] = 0;
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= d; ++j) {
                int mx = 0;
                for (int k = i; k > 0; --k) {
                    mx = Math.max(mx, jobDifficulty[k - 1]);
                    f[i][j] = Math.min(f[i][j], f[k - 1][j - 1] + mx);
                }
            }
        }
        return f[n][d] < inf ? f[n][d] : -1;
    }
}