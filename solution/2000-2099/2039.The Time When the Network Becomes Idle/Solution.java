class Solution {
    public int networkBecomesIdle(int[][] edges, int[] patience) {
        int n = patience.length;
        List<Integer>[] g = new List[n];
        boolean[] vis = new boolean[n];
        for (int i = 0; i < n; ++i) {
            g[i] = new ArrayList<>();
        }
        for (int[] e : edges) {
            int u = e[0], v = e[1];
            g[u].add(v);
            g[v].add(u);
        }
        int ans = 0;
        int step = 0;
        Deque<Integer> q = new ArrayDeque<>();
        q.offer(0);
        vis[0] = true;
        while (!q.isEmpty()) {
            ++step;
            for (int i = q.size(); i > 0; --i) {
                int u = q.poll();
                for (int v : g[u]) {
                    if (vis[v]) {
                        continue;
                    }
                    vis[v] = true;
                    q.offer(v);
                    int d = step * 2;
                    int t = patience[v];
                    ans = Math.max(ans, (d - 1) / t * t + d + 1);
                }
            }
        }
        return ans;
    }
}