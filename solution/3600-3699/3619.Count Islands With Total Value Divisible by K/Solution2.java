class Solution {
    public int countIslands(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        int[] dirs = {-1, 0, 1, 0, -1};
        Deque<int[]> stk = new ArrayDeque<>();
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 0) {
                    continue;
                }
                long s = grid[i][j];
                grid[i][j] = 0;
                stk.push(new int[] {i, j});
                while (!stk.isEmpty()) {
                    int[] p = stk.pop();
                    int x0 = p[0], y0 = p[1];
                    for (int d = 0; d < 4; ++d) {
                        int x = x0 + dirs[d], y = y0 + dirs[d + 1];
                        if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] > 0) {
                            s += grid[x][y];
                            grid[x][y] = 0;
                            stk.push(new int[] {x, y});
                        }
                    }
                }
                if (s % k == 0) {
                    ++ans;
                }
            }
        }
        return ans;
    }
}
