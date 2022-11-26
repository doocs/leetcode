class Solution {
    private List<Integer>[] g;
    private int[] f;
    private boolean[] vis;
    private int k;

    public boolean leadsToDestination(int n, int[][] edges, int source, int destination) {
        vis = new boolean[n];
        g = new List[n];
        k = destination;
        f = new int[n];
        Arrays.setAll(g, key -> new ArrayList<>());
        for (var e : edges) {
            g[e[0]].add(e[1]);
        }
        return dfs(source);
    }

    private boolean dfs(int i) {
        if (i == k) {
            return g[i].isEmpty();
        }
        if (f[i] != 0) {
            return f[i] == 1;
        }
        if (vis[i] || g[i].isEmpty()) {
            return false;
        }
        vis[i] = true;
        for (int j : g[i]) {
            if (!dfs(j)) {
                f[i] = -1;
                return false;
            }
        }
        f[i] = 1;
        return true;
    }
}