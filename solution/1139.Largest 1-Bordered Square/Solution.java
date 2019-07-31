class Solution {
    public int largest1BorderedSquare(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] down = new int[m][n];
        int[][] right = new int[m][n];
        for (int i = m - 1; i >= 0; --i) {
            for (int j = n - 1; j >= 0; --j) {
                if (grid[i][j] == 1) {
                    down[i][j] += i + 1 < m ? down[i + 1][j] + 1 : 1;
                    right[i][j] += j + 1 < n ? right[i][j + 1] + 1 : 1;
                }
            }
        }
        for (int len = Math.min(m, n); len > 0; --len) {
            for (int i = 0; i <= m - len; ++i) {
                for (int j = 0; j <= n - len; ++j) {
                    if (down[i][j] >= len && right[i][j] >= len && right[i + len - 1][j] >= len && down[i][j + len - 1] >= len) {
                        return len * len;
                    }
                }
            }
        }
        return 0;
    }
}
