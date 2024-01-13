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
        int[] d1 = f(node1);
        int[] d2 = f(node2);
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

    private int[] f(int i) {
        int[] dist = new int[n];
        Arrays.fill(dist, 1 << 30);
        dist[i] = 0;
        Deque<Integer> q = new ArrayDeque<>();
        q.offer(i);
        while (!q.isEmpty()) {
            i = q.poll();
            for (int j : g[i]) {
                if (dist[j] == 1 << 30) {
                    dist[j] = dist[i] + 1;
                    q.offer(j);
                }
            }
        }
        return dist;
    }
}