class Solution {
    private boolean[] vis;
    private List<Integer>[] g;

    public boolean validPath(int n, int[][] edges, int source, int destination) {
        vis = new boolean[n];
        g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (var e : edges) {
            int a = e[0], b = e[1];
            g[a].add(b);
            g[b].add(a);
        }
        return dfs(source, destination);
    }

    private boolean dfs(int source, int destination) {
        if (source == destination) {
            return true;
        }
        vis[source] = true;
        for (int nxt : g[source]) {
            if (!vis[nxt] && dfs(nxt, destination)) {
                return true;
            }
        }
        return false;
    }
}