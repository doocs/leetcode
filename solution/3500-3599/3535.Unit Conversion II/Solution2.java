class Solution {
    private final int mod = (int) 1e9 + 7;

    public int[] queryConversions(int[][] conversions, int[][] queries) {
        int n = conversions.length + 1;
        List<int[]>[] g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (var e : conversions) {
            g[e[0]].add(new int[] {e[1], e[2]});
        }
        int[] res = new int[n];
        Deque<long[]> stk = new ArrayDeque<>();
        stk.push(new long[] {0, 1});
        while (!stk.isEmpty()) {
            long[] cur = stk.pop();
            int s = (int) cur[0];
            long mul = cur[1];
            res[s] = (int) mul;
            for (var e : g[s]) {
                stk.push(new long[] {e[0], mul * e[1] % mod});
            }
        }
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int x = queries[i][0], y = queries[i][1];
            ans[i] = (int) ((long) res[y] * qpow(res[x], mod - 2) % mod);
        }
        return ans;
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
