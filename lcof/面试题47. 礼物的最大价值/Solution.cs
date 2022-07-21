public class Solution {
    public int MaxValue(int[][] grid) {
        int m = grid.Length, n = grid[0].Length;
        int[,] dp = new int[m+1,n+1];
        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                dp[i,j] = Math.Max(dp[i-1,j], dp[i,j-1]) + grid[i-1][j-1];
            }
        }
        return dp[m,n];

    }
}