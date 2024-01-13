class Solution {
    private final int mod = (int) 1e9 + 7;

    public int waysToStep(int n) {
        if (n < 4) {
            return (int) Math.pow(2, n - 1);
        }
        long[][] a = {{1, 1, 0}, {1, 0, 1}, {1, 0, 0}};
        long[][] res = pow(a, n - 4);
        long ans = 0;
        for (long x : res[0]) {
            ans = (ans + x) % mod;
        }
        return (int) ans;
    }

    private long[][] mul(long[][] a, long[][] b) {
        int m = a.length, n = b[0].length;
        long[][] c = new long[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                for (int k = 0; k < b.length; ++k) {
                    c[i][j] = (c[i][j] + a[i][k] * b[k][j] % mod) % mod;
                }
            }
        }
        return c;
    }

    private long[][] pow(long[][] a, int n) {
        long[][] res = {{4, 2, 1}};
        while (n > 0) {
            if ((n & 1) == 1) {
                res = mul(res, a);
            }
            a = mul(a, a);
            n >>= 1;
        }
        return res;
    }
}