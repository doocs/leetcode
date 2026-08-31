class Solution {
    private char[] cs;
    private long[][][][] cnt;
    private long[][][][] wav;

    public long totalWaviness(long num1, long num2) {
        return calc(num2) - calc(num1 - 1);
    }

    private long calc(long x) {
        if (x < 0) {
            return 0;
        }
        cs = Long.toString(x).toCharArray();
        int n = cs.length;
        cnt = new long[n][11][11][2];
        wav = new long[n][11][11][2];
        for (int i = 0; i < n; ++i) {
            for (int a = 0; a < 11; ++a) {
                for (int b = 0; b < 11; ++b) {
                    Arrays.fill(cnt[i][a][b], -1);
                    Arrays.fill(wav[i][a][b], -1);
                }
            }
        }
        return dfs(0, 10, 10, 0, true)[1];
    }

    private long[] dfs(int pos, int prev2, int prev1, int started, boolean limit) {
        if (pos == cs.length) {
            return new long[] {started, 0};
        }
        if (!limit && cnt[pos][prev2][prev1][started] != -1) {
            return new long[] {cnt[pos][prev2][prev1][started], wav[pos][prev2][prev1][started]};
        }
        int up = limit ? cs[pos] - '0' : 9;
        long c = 0, w = 0;
        for (int d = 0; d <= up; ++d) {
            boolean nlimit = limit && d == up;
            int ns, np2, np1, add = 0;
            if (started == 0) {
                if (d == 0) {
                    ns = 0;
                    np2 = 10;
                    np1 = 10;
                } else {
                    ns = 1;
                    np2 = 10;
                    np1 = d;
                }
            } else {
                ns = 1;
                np2 = prev1;
                np1 = d;
                if (prev2 != 10 && ((prev1 > prev2 && prev1 > d) || (prev1 < prev2 && prev1 < d))) {
                    add = 1;
                }
            }
            long[] t = dfs(pos + 1, np2, np1, ns, nlimit);
            c += t[0];
            w += t[1] + t[0] * add;
        }
        if (!limit) {
            cnt[pos][prev2][prev1][started] = c;
            wav[pos][prev2][prev1][started] = w;
        }
        return new long[] {c, w};
    }
}
