class Solution {
    public int maxDistance(int[][] grid) {
        int n = grid.length;
        Deque<int[]> q = new LinkedList<>();
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 1) {
                    q.offer(new int[]{i, j});
                }
            }
        }
        int ans = -1;
        boolean valid = false;
        int[] dirs = {-1, 0, 1, 0, -1};
        while (!q.isEmpty()) {
            ++ans;
            for (int k = q.size(); k > 0; --k) {
                int[] p = q.poll();
                for (int i = 0; i < 4; ++i) {
                    int x = p[0] + dirs[i];
                    int y = p[1] + dirs[i + 1];
                    if (x >= 0 && x < n && y >= 0 && y < n && grid[x][y] == 0) {
                        valid = true;
                        grid[x][y] = 1;
                        q.offer(new int[]{x, y});
                    }
                }
            }
        }
        return valid ? ans : -1;
    }
}