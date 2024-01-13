class Solution {
    private boolean[][] vis;
    private int[][] maze;
    private int[] d;
    private int m;
    private int n;

    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        m = maze.length;
        n = maze[0].length;
        d = destination;
        this.maze = maze;
        vis = new boolean[m][n];
        dfs(start[0], start[1]);
        return vis[d[0]][d[1]];
    }

    private void dfs(int i, int j) {
        if (vis[i][j]) {
            return;
        }
        vis[i][j] = true;
        if (i == d[0] && j == d[1]) {
            return;
        }
        int[] dirs = {-1, 0, 1, 0, -1};
        for (int k = 0; k < 4; ++k) {
            int x = i, y = j;
            int a = dirs[k], b = dirs[k + 1];
            while (x + a >= 0 && x + a < m && y + b >= 0 && y + b < n && maze[x + a][y + b] == 0) {
                x += a;
                y += b;
            }
            dfs(x, y);
        }
    }
}