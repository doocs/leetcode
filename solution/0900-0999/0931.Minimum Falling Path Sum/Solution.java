class Solution {
    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                int mi = matrix[i - 1][j];
                if (j > 0) {
                    mi = Math.min(mi, matrix[i - 1][j - 1]);
                }
                if (j < n - 1) {
                    mi = Math.min(mi, matrix[i - 1][j + 1]);
                }
                matrix[i][j] += mi;
            }
        }
        int res = Integer.MAX_VALUE;
        for (int j = 0; j < n; ++j) {
            res = Math.min(res, matrix[n - 1][j]);
        }
        return res;
    }
}