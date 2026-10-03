class Solution {
    public int[] maxTargetNodes(int[][] edges1, int[][] edges2) {
        var g1 = build(edges1);
        var g2 = build(edges2);
        int n = g1.length, m = g2.length;
        int[] c1 = new int[n];
        int[] c2 = new int[m];
        int[] cnt1 = new int[2];
        int[] cnt2 = new int[2];
        dfs(g2, c2, cnt2);
        dfs(g1, c1, cnt1);
        int t = Math.max(cnt2[0], cnt2[1]);
        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            ans[i] = t + cnt1[c1[i]];
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

    private void dfs(List<Integer>[] g, int[] c, int[] cnt) {
        Deque<int[]> stk = new ArrayDeque<>();
        stk.push(new int[] {0, -1, 0});
        while (!stk.isEmpty()) {
            int[] cur = stk.pop();
            int a = cur[0], fa = cur[1], d = cur[2];
            c[a] = d;
            cnt[d]++;
            for (int b : g[a]) {
                if (b != fa) {
                    stk.push(new int[] {b, a, d ^ 1});
                }
            }
        }
    }
}
