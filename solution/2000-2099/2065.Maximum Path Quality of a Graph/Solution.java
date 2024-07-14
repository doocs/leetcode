class Solution {
    private List<int[]>[] g;
    private boolean[] vis;
    private int[] values;
    private int maxTime;
    private int ans;

    public int maximalPathQuality(int[] values, int[][] edges, int maxTime) {
        int n = values.length;
        g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (var e : edges) {
            int u = e[0], v = e[1], t = e[2];
            g[u].add(new int[] {v, t});
            g[v].add(new int[] {u, t});
        }
        vis = new boolean[n];
        vis[0] = true;
        this.values = values;
        this.maxTime = maxTime;
        dfs(0, 0, values[0]);
        return ans;
    }

    private void dfs(int u, int cost, int value) {
        if (u == 0) {
            ans = Math.max(ans, value);
        }
        for (var e : g[u]) {
            int v = e[0], t = e[1];
            if (cost + t <= maxTime) {
                if (vis[v]) {
                    dfs(v, cost + t, value);
                } else {
                    vis[v] = true;
                    dfs(v, cost + t, value + values[v]);
                    vis[v] = false;
                }
            }
        }
    }
}