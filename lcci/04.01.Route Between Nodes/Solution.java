class Solution {
    private List<Integer>[] g;
    private boolean[] vis;
    private int target;

    public boolean findWhetherExistsPath(int n, int[][] graph, int start, int target) {
        vis = new boolean[n];
        g = new List[n];
        this.target = target;
        Arrays.setAll(g, k -> new ArrayList<>());
        for (int[] e : graph) {
            g[e[0]].add(e[1]);
        }
        return dfs(start);
    }

    private boolean dfs(int i) {
        if (i == target) {
            return true;
        }
        if (vis[i]) {
            return false;
        }
        vis[i] = true;
        for (int j : g[i]) {
            if (dfs(j)) {
                return true;
            }
        }
        return false;
    }
}