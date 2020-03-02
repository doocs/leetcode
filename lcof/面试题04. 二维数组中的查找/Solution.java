class Solution {
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
            return false;
        }
        int rows = matrix.length, cols = matrix[0].length;

        int i = rows - 1, j = 0;
        while (i >= 0 && j < cols) {
            if (matrix[i][j] == target) {
                return true;
            }
            if (matrix[i][j] < target) {
                ++j;
            } else {
                --i;
            }
        }
        return false;
    }
}