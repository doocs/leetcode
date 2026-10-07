class Solution {
    public int numEnclaves(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[] dirs = {-1, 0, 1, 0, -1};
        Deque<int[]> stk = new ArrayDeque<>();
        for (int j = 0; j < n; j++) {
            for (int i : List.of(0, m - 1)) {
                if (grid[i][j] == 1) {
                    flood(grid, stk, dirs, i, j);
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j : List.of(0, n - 1)) {
                if (grid[i][j] == 1) {
                    flood(grid, stk, dirs, i, j);
                }
            }
        }
        int ans = 0;
        for (var row : grid) {
            for (int x : row) {
                ans += x;
            }
        }
        return ans;
    }

    private void flood(int[][] grid, Deque<int[]> stk, int[] dirs, int i, int j) {
        int m = grid.length, n = grid[0].length;
        grid[i][j] = 0;
        stk.push(new int[] {i, j});
        while (!stk.isEmpty()) {
            int[] cur = stk.pop();
            i = cur[0];
            j = cur[1];
            for (int k = 0; k < 4; k++) {
                int x = i + dirs[k], y = j + dirs[k + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1) {
                    grid[x][y] = 0;
                    stk.push(new int[] {x, y});
                }
            }
        }
    }
}
