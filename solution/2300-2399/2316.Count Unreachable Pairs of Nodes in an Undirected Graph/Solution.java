class Solution {
    private boolean[] vis;
    private List<Integer>[] g;

    public long countPairs(int n, int[][] edges) {
        vis = new boolean[n];
        g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (var e : edges) {
            int a = e[0], b = e[1];
            g[a].add(b);
            g[b].add(a);
        }
        long ans = 0, s = 0;
        for (int i = 0; i < n; ++i) {
            if (!vis[i]) {
                long t = dfs(i);
                ans += s * t;
                s += t;
            }
        }
        return ans;
    }

    private int dfs(int i) {
        vis[i] = true;
        int cnt = 1;
        for (int j : g[i]) {
            if (!vis[j]) {
                cnt += dfs(j);
            }
        }
        return cnt;
    }
}