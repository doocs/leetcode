class Solution {
    private static final long INF = Long.MAX_VALUE / 2;
    private static final int MOD = (int) 1e9 + 7;

    public int countPaths(int n, int[][] roads) {
        long[][] g = new long[n][n];
        long[] dist = new long[n];
        long[] w = new long[n];
        boolean[] vis = new boolean[n];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(g[i], INF);
            Arrays.fill(dist, INF);
        }
        for (int[] r : roads) {
            int u = r[0], v = r[1], t = r[2];
            g[u][v] = t;
            g[v][u] = t;
        }
        g[0][0] = 0;
        dist[0] = 0;
        w[0] = 1;
        for (int i = 0; i < n; ++i) {
            int t = -1;
            for (int j = 0; j < n; ++j) {
                if (!vis[j] && (t == -1 || dist[j] < dist[t])) {
                    t = j;
                }
            }
            vis[t] = true;
            for (int j = 0; j < n; ++j) {
                if (j == t) {
                    continue;
                }
                long ne = dist[t] + g[t][j];
                if (dist[j] > ne) {
                    dist[j] = ne;
                    w[j] = w[t];
                } else if (dist[j] == ne) {
                    w[j] = (w[j] + w[t]) % MOD;
                }
            }
        }
        return (int) w[n - 1];
    }
}