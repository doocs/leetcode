class Solution {
    public int numberOfStableArrays(int zero, int one, int limit) {
        final int mod = (int) 1e9 + 7;
        long[][][] f = new long[zero + 1][one + 1][2];
        for (int i = 1; i <= Math.min(zero, limit); ++i) {
            f[i][0][0] = 1;
        }
        for (int j = 1; j <= Math.min(one, limit); ++j) {
            f[0][j][1] = 1;
        }
        for (int i = 1; i <= zero; ++i) {
            for (int j = 1; j <= one; ++j) {
                long x = i - limit - 1 < 0 ? 0 : f[i - limit - 1][j][1];
                long y = j - limit - 1 < 0 ? 0 : f[i][j - limit - 1][0];
                f[i][j][0] = (f[i - 1][j][0] + f[i - 1][j][1] - x + mod) % mod;
                f[i][j][1] = (f[i][j - 1][0] + f[i][j - 1][1] - y + mod) % mod;
            }
        }
        return (int) ((f[zero][one][0] + f[zero][one][1]) % mod);
    }
}
