class Solution {
    private static final int INF = 0x3f3f;

    public int networkDelayTime(int[][] times, int n, int k) {
        int[][] g = new int[n][n];
        int[] dist = new int[n];
        boolean[] vis = new boolean[n];
        for (int i = 0; i < n; ++i) {
            dist[i] = INF;
            Arrays.fill(g[i], INF);
        }
        for (int[] t : times) {
            g[t[0] - 1][t[1] - 1] = t[2];
        }
        dist[k - 1] = 0;
        for (int i = 0; i < n; ++i) {
            int t = -1;
            for (int j = 0; j < n; ++j) {
                if (!vis[j] && (t == -1 || dist[t] > dist[j])) {
                    t = j;
                }
            }
            vis[t] = true;
            for (int j = 0; j < n; ++j) {
                dist[j] = Math.min(dist[j], dist[t] + g[t][j]);
            }
        }
        int ans = 0;
        for (int d : dist) {
            ans = Math.max(ans, d);
        }
        return ans == INF ? -1 : ans;
    }
}