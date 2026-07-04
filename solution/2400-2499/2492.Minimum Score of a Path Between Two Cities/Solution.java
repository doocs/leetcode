class Solution {
    private int ans;
    private boolean[] vis;
    private List<int[]>[] g;

    public int minScore(int n, int[][] roads) {
        g = new ArrayList[n + 1];
        Arrays.setAll(g, k -> new ArrayList<>());

        for (int[] e : roads) {
            int a = e[0], b = e[1], w = e[2];
            g[a].add(new int[] {b, w});
            g[b].add(new int[] {a, w});
        }

        ans = Integer.MAX_VALUE;
        vis = new boolean[n + 1];

        dfs(1);
        return ans;
    }

    private void dfs(int a) {
        vis[a] = true;
        for (int[] nb : g[a]) {
            int b = nb[0], w = nb[1];
            ans = Math.min(ans, w);
            if (!vis[b]) {
                dfs(b);
            }
        }
    }
}