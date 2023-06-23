class Solution {
    public int minimumDistance(int n, List<List<Integer>> edges, int s, int[] marked) {
        final int inf = 1 << 29;
        int[][] g = new int[n][n];
        for (var e : g) {
            Arrays.fill(e, inf);
        }
        for (var e : edges) {
            int u = e.get(0), v = e.get(1), w = e.get(2);
            g[u][v] = Math.min(g[u][v], w);
        }
        int[] dist = new int[n];
        Arrays.fill(dist, inf);
        dist[s] = 0;
        boolean[] vis = new boolean[n];
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
        int ans = inf;
        for (int i : marked) {
            ans = Math.min(ans, dist[i]);
        }
        return ans >= inf ? -1 : ans;
    }
}