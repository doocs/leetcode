class Solution {
    public long minMergeCost(int[][] lists) {
        int n = lists.length;
        int tot = 0;
        for (int[] v : lists) {
            tot += v.length;
        }
        int[] vals = new int[tot];
        int p = 0;
        for (int[] v : lists) {
            for (int x : v) {
                vals[p++] = x;
            }
        }
        Arrays.sort(vals);
        int m = 0;
        for (int i = 0; i < tot; ++i) {
            if (m == 0 || vals[i] != vals[m - 1]) {
                vals[m++] = vals[i];
            }
        }
        int[] cnt = new int[1 << n];
        int[] med = new int[1 << n];
        for (int i = 1; i < 1 << n; ++i) {
            for (int j = 0; j < n; ++j) {
                if ((i >> j & 1) == 1) {
                    cnt[i] += lists[j].length;
                }
            }
            int need = (cnt[i] + 1) / 2;
            int l = 0, r = m - 1;
            while (l < r) {
                int mid = (l + r) >> 1;
                int le = 0;
                for (int b = i; b > 0; b &= b - 1) {
                    int id = Integer.numberOfTrailingZeros(b);
                    le += upperBound(lists[id], vals[mid]);
                    if (le >= need) {
                        break;
                    }
                }
                if (le >= need) {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }
            med[i] = vals[l];
        }

        long[] f = new long[1 << n];
        Arrays.fill(f, Long.MAX_VALUE / 4);
        for (int i = 1; i < 1 << n; ++i) {
            if (Integer.bitCount(i) == 1) {
                f[i] = 0;
                continue;
            }
            for (int j = (i - 1) & i; j > 0; j = (j - 1) & i) {
                int k = i ^ j;
                if (j <= k) {
                    f[i] = Math.min(f[i], f[j] + f[k] + Math.abs(med[j] - med[k]));
                }
            }
            f[i] += cnt[i];
        }
        return f[(1 << n) - 1];
    }

    private int upperBound(int[] a, int x) {
        int l = 0, r = a.length;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (a[mid] <= x) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }
}
