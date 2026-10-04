class Solution {
    public int numberOfWays(String corridor) {
        final int mod = (int) 1e9 + 7;
        int n = corridor.length();
        int[][] f = new int[n + 1][3];
        f[n][2] = 1;
        for (int i = n - 1; i >= 0; --i) {
            for (int k = 0; k < 3; ++k) {
                int nk = k + (corridor.charAt(i) == 'S' ? 1 : 0);
                if (nk > 2) {
                    continue;
                }
                f[i][k] = f[i + 1][nk];
                if (nk == 2) {
                    f[i][k] = (f[i][k] + f[i + 1][0]) % mod;
                }
            }
        }
        return f[0][0];
    }
}
