class Solution {
    private static final int N = 110;
    private static final int INF = 0x3f3f;

    public int networkDelayTime(int[][] times, int n, int k) {
        int[][] g = new int[N][N];
        for (int i = 0; i < N; ++i) {
            Arrays.fill(g[i], INF);
        }
        for (int[] e : times) {
            g[e[0]][e[1]] = e[2];
        }
        int[] dist = new int[N];
        Arrays.fill(dist, INF);
        dist[k] = 0;
        boolean[] vis = new boolean[N];
        for (int i = 0; i < n; ++i) {
            int t = -1;
            for (int j = 1; j <= n; ++j) {
                if (!vis[j] && (t == -1 || dist[t] > dist[j])) {
                    t = j;
                }
            }
            vis[t] = true;
            for (int j = 1; j <= n; ++j) {
                dist[j] = Math.min(dist[j], dist[t] + g[t][j]);
            }
        }
        int ans = 0;
        for (int i = 1; i <= n; ++i) {
            ans = Math.max(ans, dist[i]);
        }
        return ans == INF ? -1 : ans;
    }
}