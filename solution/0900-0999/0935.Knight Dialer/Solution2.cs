public class Solution {
    private const int mod = 1000000007;
    private readonly int[][] baseMatrix = {
        new int[] {0, 0, 0, 0, 1, 0, 1, 0, 0, 0},
        new int[] {0, 0, 0, 0, 0, 0, 1, 0, 1, 0},
        new int[] {0, 0, 0, 0, 0, 0, 0, 1, 0, 1},
        new int[] {0, 0, 0, 0, 1, 0, 0, 0, 1, 0},
        new int[] {1, 0, 0, 1, 0, 0, 0, 0, 0, 1},
        new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        new int[] {1, 1, 0, 0, 0, 0, 0, 1, 0, 0},
        new int[] {0, 0, 1, 0, 0, 0, 1, 0, 0, 0},
        new int[] {0, 1, 0, 1, 0, 0, 0, 0, 0, 0},
        new int[] {0, 0, 1, 0, 1, 0, 0, 0, 0, 0}
    };

    public int KnightDialer(int n) {
        int[][] res = Pow(baseMatrix, n - 1);
        int ans = 0;
        foreach (var x in res[0]) {
            ans = (ans + x) % mod;
        }
        return ans;
    }

    private int[][] Mul(int[][] a, int[][] b) {
        int m = a.Length, n = b[0].Length;
        int[][] c = new int[m][];
        for (int i = 0; i < m; i++) {
            c[i] = new int[n];
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < b.Length; k++) {
                    c[i][j] = (int)((c[i][j] + (long)a[i][k] * b[k][j]) % mod);
                }
            }
        }
        return c;
    }

    private int[][] Pow(int[][] a, int n) {
        int size = a.Length;
        int[][] res = new int[1][];
        res[0] = new int[size];
        for (int i = 0; i < size; i++) {
            res[0][i] = 1;
        }

        while (n > 0) {
            if (n % 2 == 1) {
                res = Mul(res, a);
            }
            a = Mul(a, a);
            n /= 2;
        }

        return res;
    }
}
