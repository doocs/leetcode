class Solution {
    public long countPairs(int n, int[][] edges) {
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (var e : edges) {
            int a = e[0], b = e[1];
            g[a].add(b);
            g[b].add(a);
        }
        boolean[] vis = new boolean[n];
        long ans = 0, s = 0;
        for (int i = 0; i < n; ++i) {
            int t = dfs(g, vis, i);
            ans += s * t;
            s += t;
        }
        return ans;
    }

    private int dfs(List<Integer>[] g, boolean[] vis, int i) {
        if (vis[i]) {
            return 0;
        }
        vis[i] = true;
        int cnt = 0;
        Deque<Integer> stk = new ArrayDeque<>();
        stk.push(i);
        while (!stk.isEmpty()) {
            int u = stk.pop();
            ++cnt;
            for (int j : g[u]) {
                if (!vis[j]) {
                    vis[j] = true;
                    stk.push(j);
                }
            }
        }
        return cnt;
    }
}
