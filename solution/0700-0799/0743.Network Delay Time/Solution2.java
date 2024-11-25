class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        final int inf = 1 << 29;
        List<int[]>[] g = new List[n];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (var e : times) {
            g[e[0] - 1].add(new int[] {e[1] - 1, e[2]});
        }
        int[] dist = new int[n];
        Arrays.fill(dist, inf);
        dist[k - 1] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.offer(new int[] {0, k - 1});
        while (!pq.isEmpty()) {
            var p = pq.poll();
            int d = p[0], u = p[1];
            if (d > dist[u]) {
                continue;
            }
            for (var e : g[u]) {
                int v = e[0], w = e[1];
                if (dist[v] > dist[u] + w) {
                    dist[v] = dist[u] + w;
                    pq.offer(new int[] {dist[v], v});
                }
            }
        }
        int ans = Arrays.stream(dist).max().getAsInt();
        return ans == inf ? -1 : ans;
    }
}
