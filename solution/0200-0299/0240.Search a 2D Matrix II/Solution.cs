public class Solution {
    public bool SearchMatrix(int[][] matrix, int target) {
        int m = matrix.Length, n = matrix[0].Length;
        int i = m - 1, j = 0;
        while (i >= 0 && j < n)
        {
            if (matrix[i][j] == target)
            {
                return true;
            }
            if (matrix[i][j] > target)
            {
                --i;
            }
            else
            {
                ++j;
            }
        }
        return false;
    }
}