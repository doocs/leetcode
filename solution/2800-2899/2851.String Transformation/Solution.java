class Solution {
    private static final int M = 1000000007;

    private int add(int x, int y) {
        if ((x += y) >= M) {
            x -= M;
        }
        return x;
    }

    private int mul(long x, long y) {
        return (int) (x * y % M);
    }

    private int[] getZ(String s) {
        int n = s.length();
        int[] z = new int[n];
        for (int i = 1, left = 0, right = 0; i < n; ++i) {
            if (i <= right && z[i - left] <= right - i) {
                z[i] = z[i - left];
            } else {
                int z_i = Math.max(0, right - i + 1);
                while (i + z_i < n && s.charAt(i + z_i) == s.charAt(z_i)) {
                    z_i++;
                }
                z[i] = z_i;
            }
            if (i + z[i] - 1 > right) {
                left = i;
                right = i + z[i] - 1;
            }
        }
        return z;
    }

    private int[][] matrixMultiply(int[][] a, int[][] b) {
        int m = a.length, n = a[0].length, p = b[0].length;
        int[][] r = new int[m][p];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < p; ++j) {
                for (int k = 0; k < n; ++k) {
                    r[i][j] = add(r[i][j], mul(a[i][k], b[k][j]));
                }
            }
        }
        return r;
    }

    private int[][] matrixPower(int[][] a, long y) {
        int n = a.length;
        int[][] r = new int[n][n];
        for (int i = 0; i < n; ++i) {
            r[i][i] = 1;
        }
        int[][] x = new int[n][n];
        for (int i = 0; i < n; ++i) {
            System.arraycopy(a[i], 0, x[i], 0, n);
        }
        while (y > 0) {
            if ((y & 1) == 1) {
                r = matrixMultiply(r, x);
            }
            x = matrixMultiply(x, x);
            y >>= 1;
        }
        return r;
    }

    public int numberOfWays(String s, String t, long k) {
        int n = s.length();
        int[] dp = matrixPower(new int[][]{{0, 1}, {n - 1, n - 2}}, k)[0];
        s += t + t;
        int[] z = getZ(s);
        int m = n + n;
        int result = 0;
        for (int i = n; i < m; ++i) {
            if (z[i] >= n) {
                result = add(result, dp[i - n == 0 ? 0 : 1]);
            }
        }
        return result;
    }
}