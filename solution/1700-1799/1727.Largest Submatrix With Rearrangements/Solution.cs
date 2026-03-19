public class Solution {
    public int LargestSubmatrix(int[][] matrix) {
        int m = matrix.Length;
        int n = matrix[0].Length;

        for (int i = 1; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (matrix[i][j] != 0) {
                    matrix[i][j] = matrix[i - 1][j] + 1;
                }
            }
        }

        int ans = 0;

        foreach (var row in matrix) {
            Array.Sort(row);
            Array.Reverse(row);
            for (int j = 0; j < n; ++j) {
                ans = Math.Max(ans, (j + 1) * row[j]);
            }
        }

        return ans;
    }
}
