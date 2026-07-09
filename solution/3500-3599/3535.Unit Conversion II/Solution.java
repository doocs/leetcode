class Solution {
    private final int mod = (int) 1e9 + 7;
    private List<int[]>[] g;
    private int[] res;

    public int[] queryConversions(int[][] conversions, int[][] queries) {
        int n = conversions.length + 1;
        g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (var e : conversions) {
            g[e[0]].add(new int[] {e[1], e[2]});
        }

        res = new int[n];
        dfs(0, 1);

        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int x = queries[i][0], y = queries[i][1];
            ans[i] = (int) ((long) res[y] * qpow(res[x], mod - 2) % mod);
        }
        return ans;
    }

    private void dfs(int s, long mul) {
        res[s] = (int) mul;
        for (var e : g[s]) {
            dfs(e[0], mul * e[1] % mod);
        }
    }

    private long qpow(long x, int n) {
        long res = 1;
        while (n > 0) {
            if ((n & 1) == 1) {
                res = res * x % mod;
            }
            x = x * x % mod;
            n >>= 1;
        }
        return res;
    }
}