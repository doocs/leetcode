class Solution {
    public int maxValue(int[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        int[][] vals = new int[rows][cols];
        vals[0][0] = grid[0][0];
        for (int i = 1; i < rows; ++i) {
            vals[i][0] = vals[i - 1][0] + grid[i][0];
        }
        for (int j = 1; j < cols; ++j) {
            vals[0][j] = vals[0][j - 1] + grid[0][j];
        }
        for (int i = 1; i < rows; ++i) {
            for (int j = 1; j < cols; ++j) {
                vals[i][j] = grid[i][j] + Math.max(vals[i - 1][j], vals[i][j - 1]);
            }
        }
        return vals[rows - 1][cols - 1];
    }
}