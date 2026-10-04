class Solution {
    public boolean isPossibleToCutPath(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        boolean a = dfs(grid, m, n);
        grid[0][0] = 1;
        grid[m - 1][n - 1] = 1;
        boolean b = dfs(grid, m, n);
        return !(a && b);
    }

    private boolean dfs(int[][] grid, int m, int n) {
        int[] stk = new int[m * n * 2];
        int top = 0;
        stk[top++] = 0;
        while (top > 0) {
            int cur = stk[--top];
            int i = cur / n;
            int j = cur % n;
            if (i >= m || j >= n || grid[i][j] == 0) {
                continue;
            }
            grid[i][j] = 0;
            if (i == m - 1 && j == n - 1) {
                return true;
            }
            if (j + 1 < n) {
                stk[top++] = i * n + j + 1;
            }
            if (i + 1 < m) {
                stk[top++] = (i + 1) * n + j;
            }
        }
        return false;
    }
}
