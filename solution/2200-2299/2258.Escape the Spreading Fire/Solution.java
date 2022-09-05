class Solution {
    private static int[] dirs = {-1, 0, 1, 0, -1};
    private int[][] grid;
    private int m;
    private int n;

    public int maximumMinutes(int[][] grid) {
        this.grid = grid;
        m = grid.length;
        n = grid[0].length;
        int left = -1, right = m * n;
        while (left < right) {
            int mid = (left + right + 1) >> 1;
            if (check(mid)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left == m * n ? (int) 1e9 : left;
    }

    private boolean check(int t) {
        boolean[][] fire = new boolean[m][n];
        Deque<int[]> f = new ArrayDeque<>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 1) {
                    fire[i][j] = true;
                    f.offer(new int[] {i, j});
                }
            }
        }
        while (t-- > 0 && !f.isEmpty()) {
            f = spread(fire, f);
        }
        if (fire[0][0]) {
            return false;
        }
        Deque<int[]> q = new ArrayDeque<>();
        boolean[][] vis = new boolean[m][n];
        q.offer(new int[] {0, 0});
        vis[0][0] = true;
        while (!q.isEmpty()) {
            for (int i = q.size(); i > 0; --i) {
                int[] p = q.poll();
                if (fire[p[0]][p[1]]) {
                    continue;
                }
                for (int k = 0; k < 4; ++k) {
                    int x = p[0] + dirs[k], y = p[1] + dirs[k + 1];
                    if (x >= 0 && x < m && y >= 0 && y < n && !fire[x][y] && !vis[x][y]
                        && grid[x][y] == 0) {
                        if (x == m - 1 && y == n - 1) {
                            return true;
                        }
                        vis[x][y] = true;
                        q.offer(new int[] {x, y});
                    }
                }
            }
            f = spread(fire, f);
        }
        return false;
    }

    private Deque<int[]> spread(boolean[][] fire, Deque<int[]> q) {
        Deque<int[]> nf = new ArrayDeque<>();
        while (!q.isEmpty()) {
            int[] p = q.poll();
            for (int k = 0; k < 4; ++k) {
                int x = p[0] + dirs[k], y = p[1] + dirs[k + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && !fire[x][y] && grid[x][y] == 0) {
                    fire[x][y] = true;
                    nf.offer(new int[] {x, y});
                }
            }
        }
        return nf;
    }
}