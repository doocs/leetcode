class Solution {
    private int[][] cells;
    private int m;
    private int n;

    public int latestDayToCross(int row, int col, int[][] cells) {
        int l = 1, r = cells.length;
        this.cells = cells;
        this.m = row;
        this.n = col;
        while (l < r) {
            int mid = (l + r + 1) >> 1;
            if (check(mid)) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }

    private boolean check(int k) {
        int[][] g = new int[m][n];
        for (int i = 0; i < k; i++) {
            g[cells[i][0] - 1][cells[i][1] - 1] = 1;
        }
        final int[] dirs = {-1, 0, 1, 0, -1};
        Deque<int[]> q = new ArrayDeque<>();
        for (int j = 0; j < n; j++) {
            if (g[0][j] == 0) {
                q.offer(new int[] {0, j});
                g[0][j] = 1;
            }
        }
        while (!q.isEmpty()) {
            int[] p = q.poll();
            int x = p[0], y = p[1];
            if (x == m - 1) {
                return true;
            }
            for (int i = 0; i < 4; i++) {
                int nx = x + dirs[i], ny = y + dirs[i + 1];
                if (nx >= 0 && nx < m && ny >= 0 && ny < n && g[nx][ny] == 0) {
                    q.offer(new int[] {nx, ny});
                    g[nx][ny] = 1;
                }
            }
        }
        return false;
    }
}
