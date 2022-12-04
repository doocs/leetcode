class Solution {
    private List<int[]>[] g;
    private boolean[] vis;
    private int ans = 1 << 30;

    public int minScore(int n, int[][] roads) {
        g = new List[n];
        vis = new boolean[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (var e : roads) {
            int a = e[0] - 1, b = e[1] - 1, d = e[2];
            g[a].add(new int[] {b, d});
            g[b].add(new int[] {a, d});
        }
        dfs(0);
        return ans;
    }

    private void dfs(int i) {
        for (var nxt : g[i]) {
            int j = nxt[0], d = nxt[1];
            ans = Math.min(ans, d);
            if (!vis[j]) {
                vis[j] = true;
                dfs(j);
            }
        }
    }
}