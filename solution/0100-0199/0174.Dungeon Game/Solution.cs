public class Solution {
    public int CalculateMinimumHP(int[][] dungeon) {
        int m = dungeon.Length, n = dungeon[0].Length;
        int[][] dp = new int[m + 1][];
        for (int i = 0; i < m + 1; ++i) {
            dp[i] = new int[n + 1];
            Array.Fill(dp[i], 1 << 30);
        }
        dp[m][n - 1] = dp[m - 1][n] = 1;
        for (int i = m - 1; i >= 0; --i) {
            for (int j = n - 1; j >= 0; --j) {
                dp[i][j] = Math.Max(1, Math.Min(dp[i + 1][j], dp[i][j + 1]) - dungeon[i][j]);
            }
        }
        return dp[0][0];
    }
}