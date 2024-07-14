class Solution {
    private int[] coins;
    private int k;

    public long findKthSmallest(int[] coins, int k) {
        this.coins = coins;
        this.k = k;
        long l = 1, r = (long) 1e11;
        while (l < r) {
            long mid = (l + r) >> 1;
            if (check(mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    private boolean check(long mx) {
        long cnt = 0;
        int n = coins.length;
        for (int i = 1; i < 1 << n; ++i) {
            long v = 1;
            for (int j = 0; j < n; ++j) {
                if ((i >> j & 1) == 1) {
                    v = lcm(v, coins[j]);
                    if (v > mx) {
                        break;
                    }
                }
            }
            int m = Integer.bitCount(i);
            if (m % 2 == 1) {
                cnt += mx / v;
            } else {
                cnt -= mx / v;
            }
        }
        return cnt >= k;
    }

    private long lcm(long a, long b) {
        return a * b / gcd(a, b);
    }

    private long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}