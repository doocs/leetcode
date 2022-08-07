class Solution {
    private List<Integer>[] g;
    private boolean[] vis;
    private int ans;
    
    public int reachableNodes(int n, int[][] edges, int[] restricted) {
        g = new List[n];
        for (int i = 0; i < n; ++i) {
            g[i] = new ArrayList<>();
        }
        vis = new boolean[n];
        for (int v : restricted) {
            vis[v] = true;
        }
        for (int[] e : edges) {
            int a = e[0], b = e[1];
            g[a].add(b);
            g[b].add(a);
        }
        
        ans = 0;
        dfs(0);
        return ans;
    }
    
    private void dfs(int u) {
        if (vis[u]) {
            return;
        }
        ++ans;
        vis[u] = true;
        for (int v : g[u]) {
            dfs(v);
        }
    }
}