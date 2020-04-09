class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int n = obstacleGrid.length;
        int m = obstacleGrid[0].length;
        int[][] res = new int[n][m];
        int i = 0;
        while (i < n && obstacleGrid[i][0] == 0) {
            // 无障碍物
            res[i++][0] = 1;
        }
        while (i < n) {
            res[i++][0] = 0;
        }
        
        i = 0;
        while (i < m && obstacleGrid[0][i] == 0) {
            // 无障碍物
            res[0][i++] = 1;
        }
        while (i < m) {
            res[0][i++] = 0;
        }
        
        for (int k = 1; k < n; ++k) {
            for (int j = 1; j < m; ++j) {
                res[k][j] = obstacleGrid[k][j] == 1 ? 0 : (res[k - 1][j] + res[k][j - 1]);
            }
        }
        
        return res[n - 1][m - 1];
        
    }
}