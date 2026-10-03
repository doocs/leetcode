class Solution {
    public int mostProfitablePath(int[][] edges, int bob, int[] amount) {
        int n = edges.length + 1;
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (var e : edges) {
            int a = e[0], b = e[1];
            g[a].add(b);
            g[b].add(a);
        }
        int[] parent = new int[n];
        Arrays.fill(parent, -1);
        boolean[] seen = new boolean[n];
        seen[0] = true;
        Deque<Integer> stk = new ArrayDeque<>();
        stk.push(0);
        while (!stk.isEmpty()) {
            int i = stk.pop();
            for (int j : g[i]) {
                if (!seen[j]) {
                    seen[j] = true;
                    parent[j] = i;
                    stk.push(j);
                }
            }
        }
        int[] ts = new int[n];
        Arrays.fill(ts, n);
        for (int x = bob, t = 0; x != -1; x = parent[x], ++t) {
            ts[x] = t;
        }
        int ans = Integer.MIN_VALUE;
        Deque<int[]> walk = new ArrayDeque<>();
        walk.push(new int[] {0, -1, 0, 0});
        while (!walk.isEmpty()) {
            int[] f = walk.pop();
            int i = f[0], fa = f[1], t = f[2], v = f[3];
            if (t == ts[i]) {
                v += amount[i] >> 1;
            } else if (t < ts[i]) {
                v += amount[i];
            }
            if (g[i].size() == 1 && g[i].get(0) == fa) {
                ans = Math.max(ans, v);
                continue;
            }
            for (int j : g[i]) {
                if (j != fa) {
                    walk.push(new int[] {j, i, t + 1, v});
                }
            }
        }
        return ans;
    }
}
