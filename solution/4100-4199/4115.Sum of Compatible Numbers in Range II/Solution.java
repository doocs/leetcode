class Solution {
    private static final long MOD = 1_000_000_007;
    private long n, num;
    private long[][][] memo;

    private long[] dfs(int i, boolean lim) {
        if (i < 0) {
            return new long[] {1, 0};
        }
        int j = lim ? 1 : 0;
        if (memo[i][j][0] != -1) {
            return memo[i][j];
        }
        long up = lim ? (num >> i & 1) : 1;
        long cnt = 0, s = 0;
        for (int cur = 0; cur <= up; cur++) {
            if ((n >> i & 1) == 1 && cur == 1) {
                continue;
            }
            long[] res = dfs(i - 1, lim && cur == up);
            long cnt1 = res[0], s1 = res[1];
            cnt = (cnt + cnt1) % MOD;
            s = (s + s1 + cnt1 * ((1L << i) % MOD) % MOD * cur) % MOD;
        }
        return memo[i][j] = new long[] {cnt, s};
    }

    private long calc(long num, int m) {
        this.num = num;
        memo = new long[m][2][2];
        for (long[][] a : memo) {
            for (long[] b : a) {
                b[0] = -1;
            }
        }
        return dfs(m - 1, true)[1];
    }

    public int sumOfGoodIntegers(long n, long k) {
        this.n = n;
        long lo = Math.max(1, n - k), hi = n + k;
        int m = 64 - Long.numberOfLeadingZeros(hi);
        long right = calc(hi, m);
        long left = calc(lo - 1, m);
        return (int) ((right - left + MOD) % MOD);
    }
}