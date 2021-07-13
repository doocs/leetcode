class Solution {
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        int m, n;
        if (matrix == null || (m = matrix.length) == 0 || matrix[0] == null || (n = matrix[0].length) == 0) {
            return false;
        }
        for (int i = 0, j = n - 1; i < m && j >= 0;) {
            if (matrix[i][j] == target) {
                return true;
            }
            if (matrix[i][j] < target) {
                ++i;
            } else {
                --j;
            }
        }
        return false;
    }
}