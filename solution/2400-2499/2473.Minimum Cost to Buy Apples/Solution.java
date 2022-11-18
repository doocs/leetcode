class Solution {
    private int k;
    private int[] cost;
    private int[] dist;
    private List<int[]>[] g;
    private static final int INF = 0x3f3f3f3f;

    public long[] minCost(int n, int[][] roads, int[] appleCost, int k) {
        cost = appleCost;
        g = new List[n];
        dist = new int[n];
        this.k = k;
        for (int i = 0; i < n; ++i) {
            g[i] = new ArrayList<>();
        }
        for (var e : roads) {
            int a = e[0] - 1, b = e[1] - 1, c = e[2];
            g[a].add(new int[] {b, c});
            g[b].add(new int[] {a, c});
        }
        long[] ans = new long[n];
        for (int i = 0; i < n; ++i) {
            ans[i] = dijkstra(i);
        }
        return ans;
    }

    private long dijkstra(int u) {
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        q.offer(new int[] {0, u});
        Arrays.fill(dist, INF);
        dist[u] = 0;
        long ans = Long.MAX_VALUE;
        while (!q.isEmpty()) {
            var p = q.poll();
            int d = p[0];
            u = p[1];
            ans = Math.min(ans, cost[u] + (long) (k + 1) * d);
            for (var ne : g[u]) {
                int v = ne[0], w = ne[1];
                if (dist[v] > dist[u] + w) {
                    dist[v] = dist[u] + w;
                    q.offer(new int[] {dist[v], v});
                }
            }
        }
        return ans;
    }
}