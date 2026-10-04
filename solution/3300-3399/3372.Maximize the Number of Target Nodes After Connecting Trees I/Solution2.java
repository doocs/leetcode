class Solution {
    public int[] maxTargetNodes(int[][] edges1, int[][] edges2, int k) {
        var g2 = build(edges2);
        int m = edges2.length + 1;
        int t = 0;
        for (int i = 0; i < m; ++i) {
            t = Math.max(t, dfs(g2, i, -1, k - 1));
        }
        var g1 = build(edges1);
        int n = edges1.length + 1;
        int[] ans = new int[n];
        Arrays.fill(ans, t);
        for (int i = 0; i < n; ++i) {
            ans[i] += dfs(g1, i, -1, k);
        }
        return ans;
    }

    private List<Integer>[] build(int[][] edges) {
        int n = edges.length + 1;
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (var e : edges) {
            int a = e[0], b = e[1];
            g[a].add(b);
            g[b].add(a);
        }
        return g;
    }

    private int dfs(List<Integer>[] g, int a, int fa, int d) {
        if (d < 0) {
            return 0;
        }
        int cnt = 0;
        Deque<int[]> stk = new ArrayDeque<>();
        stk.push(new int[] {a, fa, d});
        while (!stk.isEmpty()) {
            int[] cur = stk.pop();
            int u = cur[0], p = cur[1], rem = cur[2];
            ++cnt;
            if (rem == 0) {
                continue;
            }
            for (int b : g[u]) {
                if (b != p) {
                    stk.push(new int[] {b, u, rem - 1});
                }
            }
        }
        return cnt;
    }
}
