class Solution {
    private static final int INF = 0x3f3f;

    public int networkDelayTime(int[][] times, int n, int k) {
        int[] dist = new int[n];
        boolean[] vis = new boolean[n];
        List<int[]>[] g = new List[n];
        for (int i = 0; i < n; ++i) {
            dist[i] = INF;
            g[i] = new ArrayList<>();
        }
        for (int[] t : times) {
            int u = t[0] - 1, v = t[1] - 1, w = t[2];
            g[u].add(new int[] {v, w});
        }
        --k;
        dist[k] = 0;
        Deque<Integer> q = new ArrayDeque<>();
        q.offer(k);
        vis[k] = true;
        while (!q.isEmpty()) {
            int u = q.poll();
            vis[u] = false;
            for (int[] ne : g[u]) {
                int v = ne[0], w = ne[1];
                if (dist[v] > dist[u] + w) {
                    dist[v] = dist[u] + w;
                    if (!vis[v]) {
                        q.offer(v);
                        vis[v] = true;
                    }
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans = Math.max(ans, dist[i]);
        }
        return ans == INF ? -1 : ans;
    }
}