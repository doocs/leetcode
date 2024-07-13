class Solution {
    private int destination;
    private boolean[] vis;
    private List<Integer>[] g;

    public boolean validPath(int n, int[][] edges, int source, int destination) {
        g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (var e : edges) {
            int a = e[0], b = e[1];
            g[a].add(b);
            g[b].add(a);
        }
        vis = new boolean[n];
        this.destination = destination;
        return dfs(source);
    }

    private boolean dfs(int i) {
        if (i == destination) {
            return true;
        }
        vis[i] = true;
        for (int j : g[i]) {
            if (!vis[j] && dfs(j)) {
                return true;
            }
        }
        return false;
    }
}
