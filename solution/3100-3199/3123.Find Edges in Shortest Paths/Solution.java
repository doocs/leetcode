class Solution {
    public boolean[] findAnswer(int n, int[][] edges) {
        List<int[]>[] g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        int m = edges.length;
        for (int i = 0; i < m; ++i) {
            int a = edges[i][0], b = edges[i][1], w = edges[i][2];
            g[a].add(new int[] {b, w, i});
            g[b].add(new int[] {a, w, i});
        }
        long[] dist = new long[n];
        final long inf = (long) 1e18;
        Arrays.fill(dist, inf);
        dist[0] = 0;
        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(a[0], b[0]));
        pq.offer(new long[] {0, 0});
        while (!pq.isEmpty()) {
            var p = pq.poll();
            long da = p[0];
            int a = (int) p[1];
            if (da > dist[a]) {
                continue;
            }
            for (var e : g[a]) {
                int b = e[0], w = e[1];
                if (dist[b] > dist[a] + w) {
                    dist[b] = dist[a] + w;
                    pq.offer(new long[] {dist[b], b});
                }
            }
        }
        boolean[] ans = new boolean[m];
        if (dist[n - 1] == inf) {
            return ans;
        }
        Deque<Integer> q = new ArrayDeque<>();
        q.offer(n - 1);
        while (!q.isEmpty()) {
            int a = q.poll();
            for (var e : g[a]) {
                int b = e[0], w = e[1], i = e[2];
                if (dist[a] == dist[b] + w) {
                    ans[i] = true;
                    q.offer(b);
                }
            }
        }
        return ans;
    }
}