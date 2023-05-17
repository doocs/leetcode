class Solution {
    public int[] findSquare(int[][] matrix) {
        int n = matrix.length;
        int[][] down = new int[n][n];
        int[][] right = new int[n][n];
        for (int i = n - 1; i >= 0; --i) {
            for (int j = n - 1; j >= 0; --j) {
                if (matrix[i][j] == 0) {
                    down[i][j] = i + 1 < n ? down[i + 1][j] + 1 : 1;
                    right[i][j] = j + 1 < n ? right[i][j + 1] + 1 : 1;
                }
            }
        }
        for (int k = n; k > 0; --k) {
            for (int i = 0; i <= n - k; ++i) {
                for (int j = 0; j <= n - k; ++j) {
                    if (down[i][j] >= k && right[i][j] >= k && right[i + k - 1][j] >= k
                        && down[i][j + k - 1] >= k) {
                        return new int[] {i, j, k};
                    }
                }
            }
        }
        return new int[0];
    }
}