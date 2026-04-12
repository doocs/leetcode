class Solution {
    private static final int MOD = 1_000_000_007;

    public int maxValue(int[] nums1, int[] nums0) {
        int n = nums1.length;
        int[][] pairs = new int[n][2];
        int b = 0;
        for (int i = 0; i < n; ++i) {
            pairs[i][0] = nums1[i];
            pairs[i][1] = nums0[i];
            b += nums1[i] + nums0[i];
        }

        Arrays.sort(pairs, (a, c) -> {
            int x1 = a[0], y1 = a[1];
            int x2 = c[0], y2 = c[1];
            int g1 = y1 == 0 ? 0 : x1 > 0 ? 1 : 2;
            int g2 = y2 == 0 ? 0 : x2 > 0 ? 1 : 2;
            if (g1 != g2) {
                return Integer.compare(g1, g2);
            }
            if (g1 == 0) {
                return Integer.compare(x2, x1);
            }
            if (g1 == 1) {
                if (x1 != x2) {
                    return Integer.compare(x2, x1);
                }
                return Integer.compare(y1, y2);
            }
            return Integer.compare(y1, y2);
        });

        long[] p = new long[b];
        p[0] = 1;
        for (int i = 1; i < b; ++i) {
            p[i] = p[i - 1] * 2 % MOD;
        }

        long ans = 0;
        --b;
        for (int[] pair : pairs) {
            int cnt1 = pair[0], cnt0 = pair[1];
            while (cnt1-- > 0) {
                ans = (ans + p[b--]) % MOD;
            }
            b -= cnt0;
        }
        return (int) ans;
    }
}
