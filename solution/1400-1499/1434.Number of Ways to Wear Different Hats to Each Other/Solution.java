class Solution {
    private static final int MOD = (int) 1e9 + 7;

    public int numberWays(List<List<Integer>> hats) {
        List<Integer>[] d = new List[41];
        for (int i = 0; i < d.length; ++i) {
            d[i] = new ArrayList<>();
        }
        int n = hats.size();
        int mx = 0;
        for (int i = 0; i < n; ++i) {
            for (int h : hats.get(i)) {
                d[h].add(i);
                mx = Math.max(mx, h);
            }
        }
        long[][] dp = new long[mx + 1][1 << n];
        dp[0][0] = 1;
        for (int i = 1; i < mx + 1; ++i) {
            for (int mask = 0; mask < 1 << n; ++mask) {
                dp[i][mask] = dp[i - 1][mask];
                for (int j : d[i]) {
                    if (((mask >> j) & 1) == 1) {
                        dp[i][mask] = (dp[i][mask] + dp[i - 1][mask ^ (1 << j)]) % MOD;
                    }
                }
            }
        }
        return (int) dp[mx][(1 << n) - 1];
    }
}