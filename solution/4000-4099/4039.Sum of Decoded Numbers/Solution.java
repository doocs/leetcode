class Solution {
    public int sumDecoded(long[] nums) {
        final long mod = 1000000007L;
        long ans = 0;

        for (long v : nums) {
            long d = v / 10;
            int w = (int) (v % 10);

            String s = Long.toString(d);
            long x = Long.parseLong(s.substring(0, w));
            long y = Long.parseLong(s.substring(w));

            ans = (ans + pow(x, y, mod)) % mod;
        }

        return (int) ans;
    }

    private long pow(long x, long y, long mod) {
        long res = 1;
        while (y > 0) {
            if ((y & 1) != 0) {
                res = res * x % mod;
            }
            x = x * x % mod;
            y >>= 1;
        }
        return res;
    }
}
