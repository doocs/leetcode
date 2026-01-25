class Solution {
    private List<Integer>[] g;
    private int n;
    private final int inf = Integer.MAX_VALUE / 2;

    public int specialNodes(int n, int[][] edges, int x, int y, int z) {
        this.n = n;
        g = new ArrayList[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (int[] e : edges) {
            int u = e[0], v = e[1];
            g[u].add(v);
            g[v].add(u);
        }

        int[] d1 = bfs(x);
        int[] d2 = bfs(y);
        int[] d3 = bfs(z);

        int ans = 0;
        for (int i = 0; i < n; i++) {
            long[] a = new long[] {d1[i], d2[i], d3[i]};
            Arrays.sort(a);
            if (a[0] * a[0] + a[1] * a[1] == a[2] * a[2]) {
                ++ans;
            }
        }
        return ans;
    }

    private int[] bfs(int i) {
        int[] dist = new int[n];
        Arrays.fill(dist, inf);
        Deque<Integer> q = new ArrayDeque<>();
        dist[i] = 0;
        q.add(i);
        while (!q.isEmpty()) {
            for (int k = q.size(); k > 0; --k) {
                int u = q.poll();
                for (int v : g[u]) {
                    if (dist[v] > dist[u] + 1) {
                        dist[v] = dist[u] + 1;
                        q.add(v);
                    }
                }
            }
        }
        return dist;
    }
}
