class Solution {
    public boolean canPartitionGrid(int[][] grid) {
        long s = 0;
        for (var row : grid) {
            for (int x : row) {
                s += x;
            }
        }
        if (s % 2 != 0) {
            return false;
        }
        int m = grid.length, n = grid[0].length;
        long pre = 0;
        for (int i = 0; i < m; ++i) {
            for (int x : grid[i]) {
                pre += x;
            }
            if (pre * 2 == s && i < m - 1) {
                return true;
            }
        }
        pre = 0;
        for (int j = 0; j < n; ++j) {
            for (int i = 0; i < m; ++i) {
                pre += grid[i][j];
            }
            if (pre * 2 == s && j < n - 1) {
                return true;
            }
        }
        return false;
    }
}