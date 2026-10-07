class Solution {
    public int stringCount(int n) {
        final int mod = (int) 1e9 + 7;
        long[][][] f = new long[2][3][2];
        f[1][2][1] = 1;
        for (int i = 0; i < n; ++i) {
            long[][][] g = new long[2][3][2];
            for (int l = 0; l < 2; ++l) {
                for (int e = 0; e < 3; ++e) {
                    for (int t = 0; t < 2; ++t) {
                        long a = f[l][e][t] * 23;
                        long b = f[Math.min(1, l + 1)][e][t];
                        long c = f[l][Math.min(2, e + 1)][t];
                        long d = f[l][e][Math.min(1, t + 1)];
                        g[l][e][t] = (a + b + c + d) % mod;
                    }
                }
            }
            f = g;
        }
        return (int) f[0][0][0];
    }
}
