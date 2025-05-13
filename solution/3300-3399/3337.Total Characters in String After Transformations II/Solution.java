class Solution {
    private final int mod = (int) 1e9 + 7;

    public int lengthAfterTransformations(String s, int t, List<Integer> nums) {
        final int m = 26;

        int[] cnt = new int[m];
        for (char c : s.toCharArray()) {
            cnt[c - 'a']++;
        }

        int[][] matrix = new int[m][m];
        for (int i = 0; i < m; i++) {
            int num = nums.get(i);
            for (int j = 1; j <= num; j++) {
                matrix[i][(i + j) % m] = 1;
            }
        }

        int[][] factor = matpow(matrix, t, m);
        int[] result = vectorMatrixMultiply(cnt, factor);
        int ans = 0;
        for (int val : result) {
            ans = (ans + val) % mod;
        }
        return ans;
    }

    private int[][] matmul(int[][] a, int[][] b) {
        int n = a.length;
        int p = b.length;
        int q = b[0].length;
        int[][] res = new int[n][q];
        for (int i = 0; i < n; i++) {
            for (int k = 0; k < p; k++) {
                if (a[i][k] == 0) continue;
                for (int j = 0; j < q; j++) {
                    res[i][j] = (int) ((res[i][j] + 1L * a[i][k] * b[k][j]) % mod);
                }
            }
        }
        return res;
    }

    private int[][] matpow(int[][] mat, int power, int m) {
        int[][] res = new int[m][m];
        for (int i = 0; i < m; i++) {
            res[i][i] = 1;
        }
        while (power > 0) {
            if ((power & 1) != 0) {
                res = matmul(res, mat);
            }
            mat = matmul(mat, mat);
            power >>= 1;
        }
        return res;
    }

    private int[] vectorMatrixMultiply(int[] vector, int[][] matrix) {
        int n = matrix.length;
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            long sum = 0;
            for (int j = 0; j < n; j++) {
                sum = (sum + 1L * vector[j] * matrix[j][i]) % mod;
            }
            result[i] = (int) sum;
        }
        return result;
    }
}
