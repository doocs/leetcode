class Solution {
    public int orangesRotting(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int cnt = 0;
        Deque<int[]> q = new LinkedList<>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 2) {
                    q.offer(new int[]{i, j});
                } else if (grid[i][j] == 1) {
                    ++cnt;
                }
            }
        }
        int ans = 0;
        int[] dirs = {1, 0, -1, 0, 1};
        while (!q.isEmpty() && cnt > 0) {
            ++ans;
            for (int i = q.size(); i > 0; --i) {
                int[] p = q.poll();
                for (int j = 0; j < 4; ++j) {
                    int x = p[0] + dirs[j];
                    int y = p[1] + dirs[j + 1];
                    if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1) {
                        grid[x][y] = 2;
                        --cnt;
                        q.offer(new int[]{x, y});
                    }
                }
            }
        }
        return cnt > 0 ? -1 : ans;
    }
}