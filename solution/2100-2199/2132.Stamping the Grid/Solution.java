class Solution {
    public boolean possibleToStamp(int[][] grid, int stampHeight, int stampWidth) {
        int m = grid.length, n = grid[0].length;
        int[][] s = new int[m + 1][n + 1];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                s[i + 1][j + 1] = s[i + 1][j] + s[i][j + 1] - s[i][j] + grid[i][j];
            }
        }
        int[][] d = new int[m + 1][n + 1];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 0) {
                    int x = i + stampHeight, y = j + stampWidth;
                    if (x <= m && y <= n && s[x][y] - s[x][j] - s[i][y] + s[i][j] == 0) {
                        d[i][j]++;
                        d[i][y]--;
                        d[x][j]--;
                        d[x][y]++;
                    }
                }
            }
        }
        int[][] cnt = new int[m + 1][n + 1];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                cnt[i + 1][j + 1] = cnt[i + 1][j] + cnt[i][j + 1] - cnt[i][j] + d[i][j];
                if (grid[i][j] == 0 && cnt[i + 1][j + 1] == 0) {
                    return false;
                }
            }
        }
        return true;
    }
}