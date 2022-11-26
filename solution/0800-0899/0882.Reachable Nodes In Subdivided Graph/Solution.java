class Solution {
    public int reachableNodes(int[][] edges, int maxMoves, int n) {
        List<int[]>[] g = new List[n];
        Arrays.setAll(g, e -> new ArrayList<>());
        for (var e : edges) {
            int u = e[0], v = e[1], cnt = e[2] + 1;
            g[u].add(new int[] {v, cnt});
            g[v].add(new int[] {u, cnt});
        }
        int[] dist = new int[n];
        Arrays.fill(dist, 1 << 30);
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        q.offer(new int[] {0, 0});
        dist[0] = 0;
        while (!q.isEmpty()) {
            var p = q.poll();
            int d = p[0], u = p[1];
            for (var nxt : g[u]) {
                int v = nxt[0], cnt = nxt[1];
                if (d + cnt < dist[v]) {
                    dist[v] = d + cnt;
                    q.offer(new int[] {dist[v], v});
                }
            }
        }
        int ans = 0;
        for (int d : dist) {
            if (d <= maxMoves) {
                ++ans;
            }
        }
        for (var e : edges) {
            int u = e[0], v = e[1], cnt = e[2];
            int a = Math.min(cnt, Math.max(0, maxMoves - dist[u]));
            int b = Math.min(cnt, Math.max(0, maxMoves - dist[v]));
            ans += Math.min(cnt, a + b);
        }
        return ans;
    }
}