public class Solution {
    public int CountSquares(int[][] matrix) {
        int m = matrix.Length;
        int n = matrix[0].Length;
        int[,] f = new int[m, n];
        int ans = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    continue;
                }
                if (i == 0 || j == 0) {
                    f[i, j] = 1;
                } else {
                    f[i, j] = Math.Min(f[i - 1, j - 1], Math.Min(f[i - 1, j], f[i, j - 1])) + 1;
                }
                ans += f[i, j];
            }
        }

        return ans;
    }
}
