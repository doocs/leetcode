class Solution {
    private Map<Integer, Integer>[] g;

    public long maxScore(int[][] edges) {
        int n = edges.length;
        g = new Map[n + 1];
        for (int i = 0; i < n + 1; ++i) {
            g[i] = new HashMap<>();
        }
        for (int i = 1; i < n; ++i) {
            int p = edges[i][0], w = edges[i][1];
            g[p].put(i, w);
        }
        return dfs(0)[1];
    }

    private long[] dfs(int i) {
        long a = 0, b = 0;
        long t = 0;
        for (int j : g[i].keySet()) {
            long[] s = dfs(j);
            a += s[1];
            b += s[1];
            t = Math.max(t, s[0] - s[1] + g[i].get(j));
        }
        b += t;
        return new long[] {a, b};
    }
}