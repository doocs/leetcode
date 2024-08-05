class Solution {
    private List<Integer>[] g;
    private int n;

    public int[] shortestDistanceAfterQueries(int n, int[][] queries) {
        this.n = n;
        g = new List[n];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (int i = 0; i < n - 1; ++i) {
            g[i].add(i + 1);
        }
        int m = queries.length;
        int[] ans = new int[m];
        for (int i = 0; i < m; ++i) {
            int u = queries[i][0], v = queries[i][1];
            g[u].add(v);
            ans[i] = bfs(0);
        }
        return ans;
    }

    private int bfs(int i) {
        Deque<Integer> q = new ArrayDeque<>();
        q.offer(i);
        boolean[] vis = new boolean[n];
        vis[i] = true;
        for (int d = 0;; ++d) {
            for (int k = q.size(); k > 0; --k) {
                int u = q.poll();
                if (u == n - 1) {
                    return d;
                }
                for (int v : g[u]) {
                    if (!vis[v]) {
                        vis[v] = true;
                        q.offer(v);
                    }
                }
            }
        }
    }
}
