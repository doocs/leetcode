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
    private static final char[] dir = {'U', 'R', 'D', 'L'};
    private static final char[] ndir = {'D', 'L', 'U', 'R'};
    private static final int[] dirs = {-1, 0, 1, 0, -1};
    private static final int N = 200;
    private static final int INF = 0x3f3f3f3f;
    private static int[][] g = new int[N][N];
    private static int[][] dist = new int[N][N];
    private int[] target;

    public int findShortestPath(GridMaster master) {
        target = new int[]{-1, -1};
        for (int i = 0; i < N; ++i) {
            Arrays.fill(g[i], -1);
            Arrays.fill(dist[i], INF);
        }
        dfs(100, 100, master);
        if (target[0] == -1 && target[1] == -1) {
            return -1;
        }
        PriorityQueue<int[]> q = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        q.offer(new int[]{0, 100, 100});
        dist[100][100] = 0;
        while (!q.isEmpty()) {
            int[] p = q.poll();
            int w = p[0], i = p[1], j = p[2];
            if (i == target[0] && j == target[1]) {
                return w;
            }
            for (int k = 0; k < 4; ++k) {
                int x = i + dirs[k], y = j + dirs[k + 1];
                if (x >= 0 && x < N && y >= 0 && y < N && g[x][y] != -1 && dist[x][y] > w + g[x][y]) {
                    dist[x][y] = w + g[x][y];
                    q.offer(new int[]{dist[x][y], x, y});
                }
            }
        }
        return 0;
    }

    private void dfs(int i, int j, GridMaster master) {
        if (master.isTarget()) {
            target = new int[]{i, j};
        }
        for (int k = 0; k < 4; ++k) {
            char d = dir[k], nd = ndir[k];
            int x = i + dirs[k], y = j + dirs[k + 1];
            if (x >= 0 && x < N && y >= 0 && y < N && master.canMove(d) && g[x][y] == -1) {
                g[x][y] = master.move(d);
                dfs(x, y, master);
                master.move(nd);
            }
        }
    }
}