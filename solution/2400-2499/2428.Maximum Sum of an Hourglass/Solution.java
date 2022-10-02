class Solution {
    public int maxSum(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int ans = 0;
        for (int i = 1; i < m - 1; ++i) {
            for (int j = 1; j < n - 1; ++j) {
                int t = 0;
                for (int x = i - 1; x <= i + 1; ++x) {
                    for (int y = j - 1; y <= j + 1; ++y) {
                        t += grid[x][y];
                    }
                }
                t -= grid[i][j - 1];
                t -= grid[i][j + 1];
                ans = Math.max(ans, t);
            }
        }
        return ans;
    }
}