class Solution {
    public int calculateMinimumHP(int[][] dungeon) {
         int row = dungeon.length, column = dungeon[0].length;
         int[][] dp = new int[row + 1][column + 1];
         for (int i = 0;i <= row;i++) {
             Arrays.fill(dp[i], Integer.MAX_VALUE);
         }
         dp[row][column - 1] = dp[row - 1][column] = 1;
         for (int i = row - 1;i > -1;i--) {
             for (int j = column - 1;j > -1;j--) {
                 int min = Math.min(dp[i][j + 1], dp[i + 1][j]);
                 dp[i][j] = Math.max(min - dungeon[i][j], 1);
             }
         }
         return dp[0][0];
     }
 }