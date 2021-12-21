class Solution {
    private int[][] grid;
    private boolean[][] vis;

    public int getMaximumGold(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        this.grid = grid;
        this.vis = new boolean[m][n];
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                ans = Math.max(ans, dfs(i, j));
            }
        }
        return ans;
    }

    private int dfs(int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 0 || vis[i][j]) {
            return 0;
        }
        vis[i][j] = true;
        int t = 0;
        int[][] dirs = new int[][]{{0, -1}, {0, 1}, {1, 0}, {-1, 0}};
        for (int[] dir : dirs) {
            t = Math.max(t, dfs(i + dir[0], j + dir[1]));
        }
        vis[i][j] = false;
        return t + grid[i][j];
    }
}