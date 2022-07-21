public class Solution {
    public bool FindNumberIn2DArray(int[][] matrix, int target) {
        if (matrix.Length == 0 || matrix[0].Length == 0) {
            return false;
        }
        int i = 0, j = matrix[0].Length - 1;
        while (i < matrix.Length && j >= 0) {
            if (target == matrix[i][j]) {
                return true;
            } else if (target > matrix[i][j]) {
                i += 1;
            } else {
                j -= 1;
            }
        }
        return false;
    }
}