class Solution {
    public String smallestGoodBase(String n) {
        long num = Long.parseLong(n);
        for (int len = 63; len >= 2; --len) {
            long radix = getRadix(len, num);
            if (radix != -1) {
                return String.valueOf(radix);
            }
        }
        return String.valueOf(num - 1);
    }

    private long getRadix(int len, long num) {
        long l = 2, r = num - 1;
        while (l < r) {
            long mid = l + r >>> 1;
            if (calc(mid, len) >= num)
                r = mid;
            else
                l = mid + 1;
        }
        return calc(r, len) == num ? r : -1;
    }

    private long calc(long radix, int len) {
        long p = 1;
        long sum = 0;
        for (int i = 0; i < len; ++i) {
            if (Long.MAX_VALUE - sum < p) {
                return Long.MAX_VALUE;
            }
            sum += p;
            if (Long.MAX_VALUE / p < radix) {
                p = Long.MAX_VALUE;
            } else {
                p *= radix;
            }
        }
        return sum;
    }
}
