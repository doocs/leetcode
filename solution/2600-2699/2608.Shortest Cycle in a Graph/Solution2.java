class Solution {
    private List<Integer>[] g;
    private final int inf = 1 << 30;

    public int findShortestCycle(int n, int[][] edges) {
        g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (var e : edges) {
            int u = e[0], v = e[1];
            g[u].add(v);
            g[v].add(u);
        }
        int ans = inf;
        for (int i = 0; i < n; ++i) {
            ans = Math.min(ans, bfs(i));
        }
        return ans < inf ? ans : -1;
    }

    private int bfs(int u) {
        int[] dist = new int[g.length];
        Arrays.fill(dist, -1);
        dist[u] = 0;
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {u, -1});
        int ans = inf;
        while (!q.isEmpty()) {
            var p = q.poll();
            u = p[0];
            int fa = p[1];
            for (int v : g[u]) {
                if (dist[v] < 0) {
                    dist[v] = dist[u] + 1;
                    q.offer(new int[] {v, u});
                } else if (v != fa) {
                    ans = Math.min(ans, dist[u] + dist[v] + 1);
                }
            }
        }
        return ans;
    }
}