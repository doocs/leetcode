class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int column = obstacleGrid[0].length, row = obstacleGrid.length;
        int[][] dp = new int[row][column];
        // 第一行
        for (int i = 0; i < column; i++) {
            if (obstacleGrid[0][i] == 1) {
                break;
            }
            dp[0][i] = 1;
        }

        // 第一列
        for (int i = 0; i < row; i++) {
            if (obstacleGrid[i][0] == 1) {
                break;
            }
            dp[i][0] = 1;
        }

        // dp
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < column; j++) {
                dp[i][j] = obstacleGrid[i][j] == 1 ? 0 : dp[i - 1][j] + dp[i][j - 1];
            }
        }

        return dp[row - 1][column - 1];
    }
}