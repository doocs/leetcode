class Solution {
    private boolean[] vis;
    private List<Integer>[] g;

    public long countPairs(int n, int[][] edges) {
        vis = new boolean[n];
        g = new List[n];
        for (int i = 0; i < n; ++i) {
            g[i] = new ArrayList<>();
        }
        for (int[] e : edges) {
            int a = e[0], b = e[1];
            g[a].add(b);
            g[b].add(a);
        }
        List<Integer> arr = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            if (!vis[i]) {
                arr.add(dfs(i));
            }
        }
        int t = 0;
        long ans = 0;
        for (int v : arr) {
            t += v;
            ans += (long) v * (n - t);
        }
        return ans;
    }

    private int dfs(int i) {
        vis[i] = true;
        int res = 1;
        for (int j : g[i]) {
            if (!vis[j]) {
                res += dfs(j);
            }
        }
        return res;
    }
}