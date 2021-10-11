class Solution {
    private int[][] dirs = new int[][]{{0, -1}, {0, 1}, {1, 0}, {-1, 0}};

    public int numIslands(char[][] grid) {
        int ans = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    dfs(grid, i, j);
                    ++ans;
                }
            }
        }
        return ans;
    }

    public void dfs(char[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != '1') {
            return;
        }
        grid[i][j] = '0';
        for (int[] dir : dirs) {
            dfs(grid, i + dir[0], j + dir[1]);
        }
    }
}