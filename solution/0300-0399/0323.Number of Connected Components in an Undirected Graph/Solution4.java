class Solution {
    public int countComponents(int n, int[][] edges) {
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (var e : edges) {
            int a = e[0], b = e[1];
            g[a].add(b);
            g[b].add(a);
        }
        boolean[] vis = new boolean[n];
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            if (vis[i]) {
                continue;
            }
            ++ans;
            Deque<Integer> stk = new ArrayDeque<>();
            stk.push(i);
            vis[i] = true;
            while (!stk.isEmpty()) {
                int u = stk.pop();
                for (int v : g[u]) {
                    if (!vis[v]) {
                        vis[v] = true;
                        stk.push(v);
                    }
                }
            }
        }
        return ans;
    }
}
