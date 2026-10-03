class Solution {
    public int[] lastMarkedNodes(int[][] edges) {
        int n = edges.length + 1;
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (var e : edges) {
            int u = e[0], v = e[1];
            g[u].add(v);
            g[v].add(u);
        }
        int[] dist1 = new int[n];
        dfs(g, 0, dist1);
        int a = maxNode(dist1);

        int[] dist2 = new int[n];
        dfs(g, a, dist2);
        int b = maxNode(dist2);

        int[] dist3 = new int[n];
        dfs(g, b, dist3);

        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            ans[i] = dist2[i] > dist3[i] ? a : b;
        }
        return ans;
    }

    private void dfs(List<Integer>[] g, int start, int[] dist) {
        Deque<int[]> stk = new ArrayDeque<>();
        stk.push(new int[] {start, -1});
        while (!stk.isEmpty()) {
            int[] cur = stk.pop();
            int i = cur[0], fa = cur[1];
            for (int j : g[i]) {
                if (j != fa) {
                    dist[j] = dist[i] + 1;
                    stk.push(new int[] {j, i});
                }
            }
        }
    }

    private int maxNode(int[] dist) {
        int mx = 0;
        for (int i = 0; i < dist.length; ++i) {
            if (dist[mx] < dist[i]) {
                mx = i;
            }
        }
        return mx;
    }
}
