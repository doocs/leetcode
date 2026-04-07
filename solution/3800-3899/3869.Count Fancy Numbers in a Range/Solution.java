class Solution {
    private String num;
    private Long[][][][] f;

    public long countFancy(long l, long r) {
        num = String.valueOf(l - 1);
        init();
        long a = dfs(0, 0, 0, 0, true);

        num = String.valueOf(r);
        init();
        long b = dfs(0, 0, 0, 0, true);

        return b - a;
    }

    private void init() {
        int n = num.length();
        f = new Long[n][9 * n + 1][10][4];
    }

    private boolean check(int s) {
        if (s < 100) {
            return s % 11 != 0;
        }
        int mid = (s / 10) % 10;
        int last = s % 10;
        return mid > 1 && mid < last;
    }

    private long dfs(int pos, int s, int prev, int st, boolean lim) {
        if (pos >= num.length()) {
            if (st != 3) {
                return 1;
            }
            return check(s) ? 1 : 0;
        }

        if (!lim && f[pos][s][prev][st] != null) {
            return f[pos][s][prev][st];
        }

        int up = lim ? num.charAt(pos) - '0' : 9;
        long res = 0;

        for (int i = 0; i <= up; i++) {
            int nxtSt = st;

            if (st == 0) {
                if (prev == 0) {
                    nxtSt = 0;
                } else if (i > prev) {
                    nxtSt = 1;
                } else if (i < prev) {
                    nxtSt = 2;
                } else {
                    nxtSt = 3;
                }
            } else if (st == 1) {
                if (i > prev) {
                    nxtSt = 1;
                } else {
                    nxtSt = 3;
                }
            } else if (st == 2) {
                if (i < prev) {
                    nxtSt = 2;
                } else {
                    nxtSt = 3;
                }
            } else {
                nxtSt = 3;
            }

            res += dfs(pos + 1, s + i, i, nxtSt, lim && i == up);
        }

        if (!lim) {
            f[pos][s][prev][st] = res;
        }

        return res;
    }
}
