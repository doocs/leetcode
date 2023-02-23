class Solution {
    private int n;
    private List<Integer>[] g;

    public int closestMeetingNode(int[] edges, int node1, int node2) {
        n = edges.length;
        g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (int i = 0; i < n; ++i) {
            if (edges[i] != -1) {
                g[i].add(edges[i]);
            }
        }
        int[] d1 = dijkstra(node1);
        int[] d2 = dijkstra(node2);
        int d = 1 << 30;
        int ans = -1;
        for (int i = 0; i < n; ++i) {
            int t = Math.max(d1[i], d2[i]);
            if (t < d) {
                d = t;
                ans = i;
            }
        }
        return ans;
    }

    private int[] dijkstra(int i) {
        int[] dist = new int[n];
        Arrays.fill(dist, 1 << 30);
        dist[i] = 0;
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        q.offer(new int[] {0, i});
        while (!q.isEmpty()) {
            var p = q.poll();
            i = p[1];
            for (int j : g[i]) {
                if (dist[j] > dist[i] + 1) {
                    dist[j] = dist[i] + 1;
                    q.offer(new int[] {dist[j], j});
                }
            }
        }
        return dist;
    }
}