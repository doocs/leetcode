class Solution {
    public int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;
        if (grid[0][0] == 1 || grid[n - 1][n - 1] == 1) {
            return -1;
        }
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] vis = new boolean[n][n];
        queue.offer(new int[]{0, 0});
        vis[0][0] = true;
        int[][] dirs = new int[][]{{0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}, {-1, 0}, {-1, 1}};
        int res = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- != 0) {
                int[] cur = queue.poll();
                if (cur[0] == n - 1 && cur[1] == n - 1) {
                    return res;
                }
                for (int[] dir : dirs) {
                    int x = cur[0] + dir[0], y = cur[1] + dir[1];
                    if (x >= 0 && x < n && y >= 0 && y < n && !vis[x][y] && grid[x][y] == 0) {
                        vis[x][y] = true;
                        queue.offer(new int[]{x, y});
                    }
                }
            }
            ++res;
        }
        return -1;
    }
}
