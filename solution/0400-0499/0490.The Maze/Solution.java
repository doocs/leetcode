class Solution {
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        int m = maze.length;
        int n = maze[0].length;
        boolean[][] vis = new boolean[m][n];
        vis[start[0]][start[1]] = true;
        Deque<int[]> q = new LinkedList<>();
        q.offer(start);
        int[] dirs = {-1, 0, 1, 0, -1};
        while (!q.isEmpty()) {
            int[] p = q.poll();
            int i = p[0], j = p[1];
            for (int k = 0; k < 4; ++k) {
                int x = i, y = j;
                int a = dirs[k], b = dirs[k + 1];
                while (
                    x + a >= 0 && x + a < m && y + b >= 0 && y + b < n && maze[x + a][y + b] == 0) {
                    x += a;
                    y += b;
                }
                if (x == destination[0] && y == destination[1]) {
                    return true;
                }
                if (!vis[x][y]) {
                    vis[x][y] = true;
                    q.offer(new int[] {x, y});
                }
            }
        }
        return false;
    }
}