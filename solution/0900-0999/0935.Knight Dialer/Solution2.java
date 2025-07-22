class Solution {
    private final int mod = (int) 1e9 + 7;
    private final int[][] base = {{0, 0, 0, 0, 1, 0, 1, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 1, 0, 1, 0},
        {0, 0, 0, 0, 0, 0, 0, 1, 0, 1}, {0, 0, 0, 0, 1, 0, 0, 0, 1, 0},
        {1, 0, 0, 1, 0, 0, 0, 0, 0, 1}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {1, 1, 0, 0, 0, 0, 0, 1, 0, 0}, {0, 0, 1, 0, 0, 0, 1, 0, 0, 0},
        {0, 1, 0, 1, 0, 0, 0, 0, 0, 0}, {0, 0, 1, 0, 1, 0, 0, 0, 0, 0}};

    public int knightDialer(int n) {
        int[][] res = pow(base, n - 1);
        int ans = 0;
        for (int x : res[0]) {
            ans = (ans + x) % mod;
        }
        return ans;
    }

    private int[][] mul(int[][] a, int[][] b) {
        int m = a.length, n = b[0].length;
        int[][] c = new int[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                for (int k = 0; k < b.length; ++k) {
                    c[i][j] = (int) ((c[i][j] + 1L * a[i][k] * b[k][j] % mod) % mod);
                }
            }
        }
        return c;
    }

    private int[][] pow(int[][] a, int n) {
        int[][] res = new int[1][a.length];
        Arrays.fill(res[0], 1);
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
