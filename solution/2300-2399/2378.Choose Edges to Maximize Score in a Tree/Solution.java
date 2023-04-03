class Solution {
    private List<int[]>[] g;

    public long maxScore(int[][] edges) {
        int n = edges.length;
        g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (int i = 1; i < n; ++i) {
            int p = edges[i][0], w = edges[i][1];
            g[p].add(new int[] {i, w});
        }
        return dfs(0)[1];
    }

    private long[] dfs(int i) {
        long a = 0, b = 0, t = 0;
        for (int[] nxt : g[i]) {
            int j = nxt[0], w = nxt[1];
            long[] s = dfs(j);
            a += s[1];
            b += s[1];
            t = Math.max(t, s[0] - s[1] + w);
        }
        b += t;
        return new long[] {a, b};
    }
}