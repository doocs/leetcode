class Solution {
    private static final int MOD = (int) 1e9 + 7;

    public int knightDialer(int n) {
        if (n == 1) {
            return 10;
        }
        long[] f = new long[10];
        Arrays.fill(f, 1);
        while (--n > 0) {
            long[] t = new long[10];
            t[0] = f[4] + f[6];
            t[1] = f[6] + f[8];
            t[2] = f[7] + f[9];
            t[3] = f[4] + f[8];
            t[4] = f[0] + f[3] + f[9];
            t[6] = f[0] + f[1] + f[7];
            t[7] = f[2] + f[6];
            t[8] = f[1] + f[3];
            t[9] = f[2] + f[4];
            for (int i = 0; i < 10; ++i) {
                f[i] = t[i] % MOD;
            }
        }
        long ans = 0;
        for (long v : f) {
            ans = (ans + v) % MOD;
        }
        return (int) ans;
    }
}