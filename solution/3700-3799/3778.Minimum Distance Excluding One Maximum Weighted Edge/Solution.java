class Solution {
    public long minCostExcludingMax(int n, int[][] edges) {
        List<int[]>[] g = new ArrayList[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (int[] e : edges) {
            int u = e[0], v = e[1], w = e[2];
            g[u].add(new int[] {v, w});
            g[v].add(new int[] {u, w});
        }

        long inf = Long.MAX_VALUE / 4;
        long[][] dist = new long[n][2];
        for (int i = 0; i < n; i++) {
            dist[i][0] = inf;
            dist[i][1] = inf;
        }
        dist[0][0] = 0;

        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[0]));
        pq.add(new long[] {0, 0, 0});

        while (!pq.isEmpty()) {
            long[] t = pq.poll();
            long cur = t[0];
            int u = (int) t[1];
            int used = (int) t[2];

            if (cur > dist[u][used]) {
                continue;
            }
            if (u == n - 1 && used == 1) {
                return cur;
            }

            for (int[] ed : g[u]) {
                int v = ed[0], w = ed[1];
                long nxt = cur + w;
                if (nxt < dist[v][used]) {
                    dist[v][used] = nxt;
                    pq.add(new long[] {nxt, v, used});
                }

                if (used == 0) {
                    nxt = cur;
                    if (nxt < dist[v][1]) {
                        dist[v][1] = nxt;
                        pq.add(new long[] {nxt, v, 1});
                    }
                }
            }
        }

        return dist[n - 1][1];
    }
}
