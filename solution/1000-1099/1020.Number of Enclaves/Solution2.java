class Solution {
    public int numEnclaves(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        Deque<int[]> q = new ArrayDeque<>();
        for (int j = 0; j < n; ++j) {
            for (int i : List.of(0, m - 1)) {
                if (grid[i][j] == 1) {
                    q.offer(new int[] {i, j});
                    grid[i][j] = 0;
                }
            }
        }
        for (int i = 0; i < m; ++i) {
            for (int j : List.of(0, n - 1)) {
                if (grid[i][j] == 1) {
                    q.offer(new int[] {i, j});
                    grid[i][j] = 0;
                }
            }
        }
        final int[] dirs = {-1, 0, 1, 0, -1};
        while (!q.isEmpty()) {
            int[] p = q.poll();
            int i = p[0], j = p[1];
            for (int k = 0; k < 4; ++k) {
                int x = i + dirs[k], y = j + dirs[k + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1) {
                    q.offer(new int[] {x, y});
                    grid[x][y] = 0;
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
}
