class Solution {
    private List<Integer>[] g;
    private boolean[] vis;

    public int countCompleteComponents(int n, int[][] edges) {
        g = new List[n];
        vis = new boolean[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (int[] e : edges) {
            int a = e[0], b = e[1];
            g[a].add(b);
            g[b].add(a);
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            if (!vis[i]) {
                int[] t = dfs(i);
                if (t[0] * (t[0] - 1) == t[1]) {
                    ++ans;
                }
            }
        }
        return ans;
    }

    private int[] dfs(int i) {
        vis[i] = true;
        int x = 1, y = g[i].size();
        for (int j : g[i]) {
            if (!vis[j]) {
                int[] t = dfs(j);
                x += t[0];
                y += t[1];
            }
        }
        return new int[] {x, y};
    }
}