class Solution {
    public boolean containsCycle(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        boolean[][] vis = new boolean[m][n];
        final int[] dirs = {-1, 0, 1, 0, -1};
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (!vis[i][j]) {
                    Deque<int[]> q = new ArrayDeque<>();
                    q.offer(new int[] {i, j, -1, -1});
                    vis[i][j] = true;
                    while (!q.isEmpty()) {
                        int[] p = q.poll();
                        int x = p[0], y = p[1], px = p[2], py = p[3];
                        for (int k = 0; k < 4; ++k) {
                            int nx = x + dirs[k], ny = y + dirs[k + 1];
                            if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
                                if (grid[nx][ny] != grid[x][y] || (nx == px && ny == py)) {
                                    continue;
                                }
                                if (vis[nx][ny]) {
                                    return true;
                                }
                                q.offer(new int[] {nx, ny, x, y});
                                vis[nx][ny] = true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
}
