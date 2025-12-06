/**
 * // This is the GridMaster's API interface.
 * // You should not implement it, or speculate about its implementation
 * class GridMaster {
 *     boolean canMove(char direction);
 *     int move(char direction);
 *     boolean isTarget();
 * }
 */

class Solution {
    private final int m = 200;
    private final int n = 200;
    private final int inf = Integer.MAX_VALUE / 2;
    private final int[] dirs = {-1, 0, 1, 0, -1};
    private final char[] s = {'U', 'R', 'D', 'L'};
    private int[][] g;
    private int sx = 100, sy = 100;
    private int tx = -1, ty = -1;
    private GridMaster master;

    public int findShortestPath(GridMaster master) {
        this.master = master;
        g = new int[m][n];
        for (var gg : g) {
            Arrays.fill(gg, -1);
        }
        dfs(sx, sy);
        if (tx == -1 && ty == -1) {
            return -1;
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        pq.offer(new int[] {0, sx, sy});
        int[][] dist = new int[m][n];
        for (var gg : dist) {
            Arrays.fill(gg, inf);
        }
        dist[sx][sy] = 0;
        while (!pq.isEmpty()) {
            var p = pq.poll();
            int w = p[0], x = p[1], y = p[2];
            if (x == tx && y == ty) {
                return w;
            }
            if (w > dist[x][y]) {
                continue;
            }
            for (int k = 0; k < 4; ++k) {
                int nx = x + dirs[k], ny = y + dirs[k + 1];
                if (nx >= 0 && nx < m && ny >= 0 && ny < n && g[nx][ny] != -1
                    && w + g[nx][ny] < dist[nx][ny]) {
                    dist[nx][ny] = w + g[nx][ny];
                    pq.offer(new int[] {dist[nx][ny], nx, ny});
                }
            }
        }
        return -1;
    }

    private void dfs(int x, int y) {
        if (master.isTarget()) {
            tx = x;
            ty = y;
        }
        for (int k = 0; k < 4; ++k) {
            int dx = dirs[k], dy = dirs[k + 1];
            int nx = x + dx, ny = y + dy;
            if (nx >= 0 && nx < m && ny >= 0 && ny < n && g[nx][ny] == -1 && master.canMove(s[k])) {
                g[nx][ny] = master.move(s[k]);
                dfs(nx, ny);
                master.move(s[(k + 2) % 4]);
            }
        }
    }
}
