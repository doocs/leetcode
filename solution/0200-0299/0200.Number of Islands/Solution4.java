class Solution {
    public int numIslands(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int ans = 0;
        int[] dirs = {-1, 0, 1, 0, -1};
        Deque<int[]> stk = new ArrayDeque<>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == '1') {
                    grid[i][j] = '0';
                    stk.push(new int[] {i, j});
                    while (!stk.isEmpty()) {
                        int[] cur = stk.pop();
                        int a = cur[0], b = cur[1];
                        for (int k = 0; k < 4; ++k) {
                            int x = a + dirs[k];
                            int y = b + dirs[k + 1];
                            if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == '1') {
                                grid[x][y] = '0';
                                stk.push(new int[] {x, y});
                            }
                        }
                    }
                    ++ans;
                }
            }
        }
        return ans;
    }
}
