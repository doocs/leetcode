class Solution {
    public int maxMoves(int[][] grid) {
        int[][] dirs = {{-1, 1}, {0, 1}, {1, 1}};
        int m = grid.length, n = grid[0].length;
        Deque<int[]> q = new ArrayDeque<>();
        for (int i = 0; i < m; ++i) {
            q.offer(new int[] {i, 0});
        }
        int[][] dist = new int[m][n];
        int ans = 0;
        while (!q.isEmpty()) {
            var p = q.poll();
            int i = p[0], j = p[1];
            for (var dir : dirs) {
                int x = i + dir[0], y = j + dir[1];
                if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] > grid[i][j]
                    && dist[x][y] < dist[i][j] + 1) {
                    dist[x][y] = dist[i][j] + 1;
                    ans = Math.max(ans, dist[x][y]);
                    q.offer(new int[] {x, y});
                }
            }
        }
        return ans;
    }
}