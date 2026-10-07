class Solution {
    public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (int[] e : edges) {
            int u = e[0], v = e[1];
            g[u].add(v);
            g[v].add(u);
        }
        int[] cost = new int[n];
        Deque<int[]> stk = new ArrayDeque<>();
        stk.push(new int[] {0, -1, 0});
        while (!stk.isEmpty()) {
            int[] cur = stk.pop();
            int u = cur[0], fa = cur[1], state = cur[2];
            if (state == 0) {
                stk.push(new int[] {u, fa, 1});
                for (int v : g[u]) {
                    if (v != fa) {
                        stk.push(new int[] {v, u, 0});
                    }
                }
            } else {
                int nxt = 0;
                for (int v : g[u]) {
                    if (v != fa) {
                        nxt += cost[v];
                    }
                }
                if (hasApple.get(u) || nxt > 0) {
                    cost[u] = u == 0 ? nxt : nxt + 2;
                }
            }
        }
        return cost[0];
    }
}
