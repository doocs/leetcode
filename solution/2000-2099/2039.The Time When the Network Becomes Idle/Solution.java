class Solution {
    public int networkBecomesIdle(int[][] edges, int[] patience) {
        int n = patience.length;
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (int[] e : edges) {
            int u = e[0], v = e[1];
            g[u].add(v);
            g[v].add(u);
        }
        Deque<Integer> q = new ArrayDeque<>();
        q.offer(0);
        boolean[] vis = new boolean[n];
        vis[0] = true;
        int ans = 0, d = 0;
        while (!q.isEmpty()) {
            ++d;
            int t = d * 2;
            for (int i = q.size(); i > 0; --i) {
                int u = q.poll();
                for (int v : g[u]) {
                    if (!vis[v]) {
                        vis[v] = true;
                        q.offer(v);
                        ans = Math.max(ans, (t - 1) / patience[v] * patience[v] + t + 1);
                    }
                }
            }
        }
        return ans;
    }
}