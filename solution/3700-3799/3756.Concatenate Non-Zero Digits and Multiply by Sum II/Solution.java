class Solution {
    private static final int MX = 100001;
    private static final int MOD = 1_000_000_007;
    private static final long[] POW10 = new long[MX];

    static {
        POW10[0] = 1;
        for (int i = 1; i < MX; i++) {
            POW10[i] = POW10[i - 1] * 10 % MOD;
        }
    }

    public int[] sumAndMultiply(String s, int[][] queries) {
        int n = s.length();
        int[] sumD = new int[n + 1];
        int[] cntN0 = new int[n + 1];
        long[] p = new long[n + 1];

        for (int i = 1; i <= n; i++) {
            int d = s.charAt(i - 1) - '0';
            sumD[i] = sumD[i - 1] + d;
            cntN0[i] = cntN0[i - 1] + (d > 0 ? 1 : 0);
            p[i] = d > 0 ? (p[i - 1] * 10 + d) % MOD : p[i - 1];
        }

        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int l = queries[i][0], r = queries[i][1];
            int n0 = cntN0[r + 1] - cntN0[l];
            int sd = sumD[r + 1] - sumD[l];
            long x = (p[r + 1] - p[l] * POW10[n0] % MOD + MOD) % MOD;
            ans[i] = (int) (x * sd % MOD);
        }
        return ans;
    }
}