class Solution {
    private int[][] grid;

    public int getMaximumGold(int[][] grid) {
        this.grid = grid;
        int ans = 0;
        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid[0].length; ++j) {
                ans = Math.max(ans, dfs(i, j));
            }
        }
        return ans;
    }

    private int dfs(int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 0) {
            return 0;
        }
        int t = grid[i][j];
        grid[i][j] = 0;
        int[] dirs = {-1, 0, 1, 0, -1};
        int res = 0;
        for (int k = 0; k < 4; ++k) {
            res = Math.max(res, t + dfs(i + dirs[k], j + dirs[k + 1]));
        }
        grid[i][j] = t;
        return res;
    }
}