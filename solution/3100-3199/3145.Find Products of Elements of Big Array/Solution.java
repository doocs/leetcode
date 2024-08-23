class Solution {
    private static final int M = 50;
    private static final long[] cnt = new long[M + 1];
    private static final long[] s = new long[M + 1];

    static {
        long p = 1;
        for (int i = 1; i <= M; i++) {
            cnt[i] = cnt[i - 1] * 2 + p;
            s[i] = s[i - 1] * 2 + p * (i - 1);
            p *= 2;
        }
    }

    private static long[] numIdxAndSum(long x) {
        long idx = 0;
        long totalSum = 0;
        while (x > 0) {
            int i = Long.SIZE - Long.numberOfLeadingZeros(x) - 1;
            idx += cnt[i];
            totalSum += s[i];
            x -= 1L << i;
            totalSum += (x + 1) * i;
            idx += x + 1;
        }
        return new long[] {idx, totalSum};
    }

    private static long f(long i) {
        long l = 0;
        long r = 1L << M;
        while (l < r) {
            long mid = (l + r + 1) >> 1;
            long[] idxAndSum = numIdxAndSum(mid);
            long idx = idxAndSum[0];
            if (idx < i) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }

        long[] idxAndSum = numIdxAndSum(l);
        long totalSum = idxAndSum[1];
        long idx = idxAndSum[0];
        i -= idx;
        long x = l + 1;
        for (int j = 0; j < i; j++) {
            long y = x & -x;
            totalSum += Long.numberOfTrailingZeros(y);
            x -= y;
        }
        return totalSum;
    }

    public int[] findProductsOfElements(long[][] queries) {
        int n = queries.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            long left = queries[i][0];
            long right = queries[i][1];
            long mod = queries[i][2];
            long power = f(right + 1) - f(left);
            ans[i] = qpow(2, power, mod);
        }
        return ans;
    }

    private int qpow(long a, long n, long mod) {
        long ans = 1 % mod;
        for (; n > 0; n >>= 1) {
            if ((n & 1) == 1) {
                ans = ans * a % mod;
            }
            a = a * a % mod;
        }
        return (int) ans;
    }
}
