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
        for (var e : edges) {
            int u = e[0], v = e[1];
            ans = Math.min(ans, bfs(u, v));
        }
        return ans < inf ? ans : -1;
    }

    private int bfs(int u, int v) {
        int[] dist = new int[g.length];
        Arrays.fill(dist, inf);
        dist[u] = 0;
        Deque<Integer> q = new ArrayDeque<>();
        q.offer(u);
        while (!q.isEmpty()) {
            int i = q.poll();
            for (int j : g[i]) {
                if ((i == u && j == v) || (i == v && j == u) || dist[j] != inf) {
                    continue;
                }
                dist[j] = dist[i] + 1;
                q.offer(j);
            }
        }
        return dist[v] + 1;
    }
}