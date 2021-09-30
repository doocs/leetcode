public class Solution {
    public int MaximalSquare(char[][] matrix) {
        int m = matrix.Length, n = matrix[0].Length;
        var dp = new int[m + 1, n + 1];
        int mx = 0;
        for (int i = 0; i < m; ++i)
        {
            for (int j = 0; j < n; ++j)
            {
                if (matrix[i][j] == '1')
                {
                    dp[i + 1, j + 1] = Math.Min(Math.Min(dp[i, j + 1], dp[i + 1, j]), dp[i, j]) + 1;
                    mx = Math.Max(mx, dp[i + 1, j + 1]);
                }
            }
        }
        return mx * mx;
    }
}