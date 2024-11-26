class Solution {
    private List<int[]>[] g;
    private int k;

    public long maximizeSumOfWeights(int[][] edges, int k) {
        this.k = k;
        int n = edges.length + 1;
        g = new List[n];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (var e : edges) {
            int u = e[0], v = e[1], w = e[2];
            g[u].add(new int[] {v, w});
            g[v].add(new int[] {u, w});
        }
        var ans = dfs(0, -1);
        return Math.max(ans[0], ans[1]);
    }

    private long[] dfs(int u, int fa) {
        long s = 0;
        List<Long> t = new ArrayList<>();
        for (var e : g[u]) {
            int v = e[0], w = e[1];
            if (v == fa) {
                continue;
            }
            var res = dfs(v, u);
            s += res[0];
            long d = w + res[1] - res[0];
            if (d > 0) {
                t.add(d);
            }
        }
        t.sort(Comparator.reverseOrder());
        for (int i = 0; i < Math.min(t.size(), k - 1); ++i) {
            s += t.get(i);
        }
        return new long[] {s + (t.size() >= k ? t.get(k - 1) : 0), s};
    }
}
