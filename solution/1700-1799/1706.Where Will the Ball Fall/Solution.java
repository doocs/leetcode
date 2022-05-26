class Solution {
    private int m;
    private int n;
    private int[][] grid;

    public int[] findBall(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        this.grid = grid;
        int[] ans = new int[n];
        for (int j = 0; j < n; ++j) {
            ans[j] = dfs(0, j);
        }
        return ans;
    }

    private int dfs(int i, int j) {
        if (i == m) {
            return j;
        }
        if (j == 0 && grid[i][j] == -1) {
            return -1;
        }
        if (j == n - 1 && grid[i][j] == 1) {
            return -1;
        }
        if (grid[i][j] == 1 && grid[i][j + 1] == -1) {
            return -1;
        }
        if (grid[i][j] == -1 && grid[i][j - 1] == 1) {
            return -1;
        }
        return grid[i][j] == 1 ? dfs(i + 1, j + 1) : dfs(i + 1, j - 1);
    }
}