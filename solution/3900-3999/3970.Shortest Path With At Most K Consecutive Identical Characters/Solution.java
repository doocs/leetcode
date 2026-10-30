class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        List<int[]>[] g = new List[n];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (int[] e : edges) {
            g[e[0]].add(new int[] {e[1], e[2]});
        }

        int[][] dist = new int[n][k + 1];
        for (int[] d : dist) {
            Arrays.fill(d, Integer.MAX_VALUE);
        }
        dist[0][1] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        pq.offer(new int[] {0, 0, 1});

        while (!pq.isEmpty()) {
            int[] p = pq.poll();
            int d = p[0], u = p[1], cur = p[2];
            if (u == n - 1) {
                return d;
            }
            if (d > dist[u][cur]) {
                continue;
            }
            for (int[] e : g[u]) {
                int v = e[0], w = e[1];
                int nxt = labels.charAt(v) != labels.charAt(u) ? 1 : cur + 1;
                int nd = d + w;
                if (nxt <= k && nd < dist[v][nxt]) {
                    dist[v][nxt] = nd;
                    pq.offer(new int[] {nd, v, nxt});
                }
            }
        }
        return -1;
    }
}