class Solution {
    public int[][] diagonalSort(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        for (int k = 0; k < Math.min(m, n) - 1; ++k) {
            for (int i = 0; i < m - 1; ++i) {
                for (int j = 0; j < n - 1; ++j) {
                    if (mat[i][j] > mat[i + 1][j + 1]) {
                        int t = mat[i][j];
                        mat[i][j] = mat[i + 1][j + 1];
                        mat[i + 1][j + 1] = t;
                    }
                }
            }
        }
        return mat;
    }
}