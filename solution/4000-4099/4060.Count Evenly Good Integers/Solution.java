class Solution {
    String s;
    long[][][] f;

    public long countEvenlyGoodIntegers(long l, long r) {
        return calc(r) - calc(l - 1);
    }

    private long calc(long x) {
        s = String.valueOf(x);
        f = new long[s.length()][2][2];
        for (long[][] a : f) {
            for (long[] b : a) {
                Arrays.fill(b, -1);
            }
        }
        return dfs(0, 0, true);
    }

    private long dfs(int pos, int st, boolean lim) {
        if (pos >= s.length()) {
            return st ^ 1;
        }
        int k = lim ? 1 : 0;
        if (f[pos][st][k] != -1) {
            return f[pos][st][k];
        }
        int up = lim ? s.charAt(pos) - '0' : 9;
        long res = 0;
        for (int i = 0; i <= up; ++i) {
            res += dfs(pos + 1, (st + (i & 1 ^ 1)) % 2, lim && i == up);
        }
        return f[pos][st][k] = res;
    }
}