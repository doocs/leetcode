class Solution {
    public int countSubIslands(int[][] grid1, int[][] grid2) {
        int m = grid1.length;
        int n = grid1[0].length;
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid2[i][j] == 1 && dfs(i, j, m, n, grid1, grid2)) {
                    ++ans;
                }
            }
        }
        return ans;
    }

    private boolean dfs(int i, int j, int m, int n, int[][] grid1, int[][] grid2) {
        boolean ans = grid1[i][j] == 1;
        grid2[i][j] = 0;
        int[] dirs = {-1, 0, 1, 0, -1};
        for (int k = 0; k < 4; ++k) {
            int x = i + dirs[k];
            int y = j + dirs[k + 1];
            if (x >= 0 && x < m && y >= 0 && y < n && grid2[x][y] == 1
                && !dfs(x, y, m, n, grid1, grid2)) {
                ans = false;
            }
        }
        return ans;
    }
}