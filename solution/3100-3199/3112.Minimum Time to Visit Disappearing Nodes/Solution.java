class Solution {
    public int[] minimumTime(int n, int[][] edges, int[] disappear) {
        List<int[]>[] g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (var e : edges) {
            int u = e[0], v = e[1], w = e[2];
            g[u].add(new int[] {v, w});
            g[v].add(new int[] {u, w});
        }
        int[] dist = new int[n];
        Arrays.fill(dist, 1 << 30);
        dist[0] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        pq.offer(new int[] {0, 0});
        while (!pq.isEmpty()) {
            var e = pq.poll();
            int du = e[0], u = e[1];
            if (du > dist[u]) {
                continue;
            }
            for (var nxt : g[u]) {
                int v = nxt[0], w = nxt[1];
                if (dist[v] > dist[u] + w && dist[u] + w < disappear[v]) {
                    dist[v] = dist[u] + w;
                    pq.offer(new int[] {dist[v], v});
                }
            }
        }
        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            ans[i] = dist[i] < disappear[i] ? dist[i] : -1;
        }
        return ans;
    }
}