class Solution {
    public int countSubIslands(int[][] grid1, int[][] grid2) {
        int m = grid1.length, n = grid1[0].length;
        int ans = 0;
        int[] dirs = {-1, 0, 1, 0, -1};
        Deque<int[]> stk = new ArrayDeque<>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid2[i][j] == 1) {
                    ans += flood(grid1, grid2, stk, dirs, i, j);
                }
            }
        }
        return ans;
    }

    private int flood(int[][] grid1, int[][] grid2, Deque<int[]> stk, int[] dirs, int i, int j) {
        int m = grid1.length, n = grid1[0].length;
        int ok = 1;
        grid2[i][j] = 0;
        stk.push(new int[] {i, j});
        while (!stk.isEmpty()) {
            int[] cur = stk.pop();
            i = cur[0];
            j = cur[1];
            ok &= grid1[i][j];
            for (int k = 0; k < 4; ++k) {
                int x = i + dirs[k], y = j + dirs[k + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && grid2[x][y] == 1) {
                    grid2[x][y] = 0;
                    stk.push(new int[] {x, y});
                }
            }
        }
        return ok;
    }
}
