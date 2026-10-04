class Solution {
    public int dieSimulator(int n, int[] rollMax) {
        final int mod = 1000000007;
        int[][][] f = new int[n + 1][7][16];
        for (int j = 0; j < 7; ++j) {
            for (int x = 0; x < 16; ++x) {
                f[n][j][x] = 1;
            }
        }
        for (int i = n - 1; i >= 0; --i) {
            for (int j = 0; j < 7; ++j) {
                for (int x = 0; x < 16; ++x) {
                    long ans = 0;
                    for (int k = 1; k <= 6; ++k) {
                        if (k != j) {
                            ans += f[i + 1][k][1];
                        } else if (x < rollMax[j - 1]) {
                            ans += f[i + 1][j][x + 1];
                        }
                    }
                    f[i][j][x] = (int) (ans % mod);
                }
            }
        }
        return f[0][0][0];
    }
}
