class Solution {
    public int countHousePlacements(int n) {
        int mod = (int) 1e9 + 7;
        long[][] f = new long[n][2];
        f[0] = new long[]{1, 1};
        for (int i = 1; i < n; ++i) {
            f[i][0] = (f[i - 1][0] + f[i - 1][1]) % mod;
            f[i][1] = f[i - 1][0];
        }
        long s = f[n - 1][0] + f[n - 1][1];
        return (int) ((s * s) % mod);
    }
}