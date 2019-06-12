class Solution {
    private int[] dirs = new int[]{-1, 0, 1, 0, -1};

    public int[][] colorBorder(int[][] grid, int r0, int c0, int color) {
        boolean[][] vis = new boolean[grid.length][grid[0].length];
        dfs(grid, r0, c0, color, vis);
        return grid;
    }

    private void dfs(int[][] grid, int i, int j, int color, boolean[][] vis) {
        vis[i][j] = true;
        int oldColor = grid[i][j];
        for (int k = 0; k < 4; ++k) {
            int x = i + dirs[k], y = j + dirs[k + 1];
            if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length) {
                if (!vis[x][y]) {
                    if (grid[x][y] == oldColor) {
                        dfs(grid, x, y, color, vis);
                    } else {
                        grid[i][j] = color;
                    }
                }
            } else {
                grid[i][j] = color;
            }
        }
    }
}
