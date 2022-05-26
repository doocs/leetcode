class Solution {
    private Map<Integer, Set<Integer>> g;
    private boolean[] vis;
    private int next;
    private int ans;

    public int treeDiameter(int[][] edges) {
        int n = edges.length;
        ans = 0;
        g = new HashMap<>();
        for (int[] e : edges) {
            g.computeIfAbsent(e[0], k -> new HashSet<>()).add(e[1]);
            g.computeIfAbsent(e[1], k -> new HashSet<>()).add(e[0]);
        }
        vis = new boolean[n + 1];
        next = edges[0][0];
        dfs(next, 0);
        vis = new boolean[n + 1];
        dfs(next, 0);
        return ans;
    }

    private void dfs(int u, int t) {
        if (vis[u]) {
            return;
        }
        vis[u] = true;
        if (ans < t) {
            ans = t;
            next = u;
        }
        for (int v : g.get(u)) {
            dfs(v, t + 1);
        }
    }
}