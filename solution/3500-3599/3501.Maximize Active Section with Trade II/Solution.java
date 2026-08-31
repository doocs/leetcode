class Solution {
    public int[] maxActiveSectionsAfterTrade(String s, int[][] queries) {
        int n = s.length();
        int active = 0;
        for (int i = 0; i < n; ++i) {
            if (s.charAt(i) == '1') {
                ++active;
            }
        }
        if (s.indexOf('0') < 0) {
            int[] ans = new int[queries.length];
            Arrays.fill(ans, active);
            return ans;
        }

        int[][] zeros = new int[n][2];
        int z = 0;
        int[] idx = new int[n];
        for (int i = 0; i < n; ++i) {
            if (s.charAt(i) == '0') {
                if (i > 0 && s.charAt(i - 1) == '0') {
                    ++zeros[z - 1][1];
                } else {
                    zeros[z][0] = i;
                    zeros[z++][1] = 1;
                }
            }
            idx[i] = z - 1;
        }

        int m = z - 1;
        int K = m > 0 ? 32 - Integer.numberOfLeadingZeros(m) : 0;
        int[][] st = new int[Math.max(K, 1)][Math.max(m, 0)];
        for (int i = 0; i < m; ++i) {
            st[0][i] = zeros[i][1] + zeros[i + 1][1];
        }
        for (int k = 1; k < K; ++k) {
            for (int i = 0; i + (1 << k) <= m; ++i) {
                st[k][i] = Math.max(st[k - 1][i], st[k - 1][i + (1 << (k - 1))]);
            }
        }

        int[] ans = new int[queries.length];
        for (int t = 0; t < queries.length; ++t) {
            int L = queries[t][0], R = queries[t][1];
            int iL = idx[L], iR = idx[R];
            int cntL = iL < 0 ? -1 : zeros[iL][1] - (L - zeros[iL][0]);
            int cntR = iR < 0 ? -1 : R - zeros[iR][0] + 1;
            int start = iL + 1;
            int end = iR - (s.charAt(R) == '0' ? 1 : 0);
            int best = active;
            if (start < end) {
                best = Math.max(best, active + query(st, m, start, end - 1));
            }
            if (s.charAt(L) == '0' && s.charAt(R) == '0' && iL + 1 == iR) {
                best = Math.max(best, active + cntL + cntR);
            }
            if (s.charAt(L) == '0' && iL + 1 < iR + (s.charAt(R) == '1' ? 1 : 0)) {
                best = Math.max(best, active + cntL + zeros[iL + 1][1]);
            }
            if (s.charAt(R) == '0' && iL < iR - 1) {
                best = Math.max(best, active + cntR + zeros[iR - 1][1]);
            }
            ans[t] = best;
        }
        return ans;
    }

    private int query(int[][] st, int m, int l, int r) {
        if (l > r || m <= 0) {
            return 0;
        }
        int k = 31 - Integer.numberOfLeadingZeros(r - l + 1);
        return Math.max(st[k][l], st[k][r - (1 << k) + 1]);
    }
}
