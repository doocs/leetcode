class Solution {
    private static final int INF = 0x3f3f3f3f;

    public int closestMeetingNode(int[] edges, int node1, int node2) {
        int n = edges.length;
        List<Integer>[] g = new List[n];
        for (int i = 0; i < n; ++i) {
            g[i] = new ArrayList<>();
        }
        for (int i = 0; i < n; ++i) {
            if (edges[i] != -1) {
                g[i].add(edges[i]);
            }
        }
        int[] d1 = dijkstra(g, node1);
        int[] d2 = dijkstra(g, node2);
        int d = INF;
        int ans = -1;
        for (int i = 0; i < n; ++i) {
            int t = Math.max(d1[i], d2[i]);
            if (d > t) {
                d = t;
                ans = i;
            }
        }
        return ans;
    }

    private int[] dijkstra(List<Integer>[] g, int u) {
        int n = g.length;
        int[] dist = new int[n];
        Arrays.fill(dist, INF);
        dist[u] = 0;
        PriorityQueue<Pair<Integer, Integer>> q = new PriorityQueue<>(Comparator.comparingInt(Pair::getKey));
        q.offer(new Pair<>(0, u));
        while (!q.isEmpty()) {
            Pair<Integer, Integer> p = q.poll();
            int d = p.getKey();
            u = p.getValue();
            if (d > dist[u]) {
                continue;
            }
            for (int v : g[u]) {
                if (dist[v] > dist[u] + 1) {
                    dist[v] = dist[u] + 1;
                    q.offer(new Pair<>(dist[v], v));
                }
            }
        }
        return dist;
    }
}