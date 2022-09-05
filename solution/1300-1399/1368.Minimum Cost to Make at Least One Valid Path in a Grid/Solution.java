class Solution {
    public int minCost(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        boolean[][] vis = new boolean[m][n];
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {0, 0, 0});
        int[][] dirs = {{0, 0}, {0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        while (!q.isEmpty()) {
            int[] p = q.poll();
            int i = p[0], j = p[1], d = p[2];
            if (i == m - 1 && j == n - 1) {
                return d;
            }
            if (vis[i][j]) {
                continue;
            }
            vis[i][j] = true;
            for (int k = 1; k <= 4; ++k) {
                int x = i + dirs[k][0], y = j + dirs[k][1];
                if (x >= 0 && x < m && y >= 0 && y < n) {
                    if (grid[i][j] == k) {
                        q.offerFirst(new int[] {x, y, d});
                    } else {
                        q.offer(new int[] {x, y, d + 1});
                    }
                }
            }
        }
        return -1;
    }
}