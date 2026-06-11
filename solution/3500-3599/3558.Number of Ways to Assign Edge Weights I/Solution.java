class Solution {
    private List<Integer>[] g;

    public int assignEdgeWeights(int[][] edges) {
        int n = edges.length + 1;
        g = new List[n + 1];
        Arrays.setAll(g, k -> new ArrayList<>());

        for (var e : edges) {
            int u = e[0];
            int v = e[1];
            g[u].add(v);
            g[v].add(u);
        }

        return (int) pow(2, dfs(1, 0) - 1, 1_000_000_007);
    }

    private int dfs(int i, int fa) {
        int res = 0;
        for (int j : g[i]) {
            if (j != fa) {
                res = Math.max(res, dfs(j, i) + 1);
            }
        }
        return res;
    }

    private long pow(long a, int n, int mod) {
        long res = 1;
        while (n > 0) {
            if ((n & 1) != 0) {
                res = res * a % mod;
            }
            a = a * a % mod;
            n >>= 1;
        }
        return res;
    }
}