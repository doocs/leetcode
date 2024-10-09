class Solution {
    private List<Integer>[] g;

    public int[] lastMarkedNodes(int[][] edges) {
        int n = edges.length + 1;
        g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (var e : edges) {
            int u = e[0], v = e[1];
            g[u].add(v);
            g[v].add(u);
        }
        int[] dist1 = new int[n];
        dist1[0] = 0;
        dfs(0, -1, dist1);
        int a = maxNode(dist1);

        int[] dist2 = new int[n];
        dist2[a] = 0;
        dfs(a, -1, dist2);
        int b = maxNode(dist2);

        int[] dist3 = new int[n];
        dist3[b] = 0;
        dfs(b, -1, dist3);

        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            ans[i] = dist2[i] > dist3[i] ? a : b;
        }
        return ans;
    }

    private void dfs(int i, int fa, int[] dist) {
        for (int j : g[i]) {
            if (j != fa) {
                dist[j] = dist[i] + 1;
                dfs(j, i, dist);
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
