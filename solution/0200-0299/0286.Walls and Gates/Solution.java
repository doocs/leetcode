class Solution {
    public void wallsAndGates(int[][] rooms) {
        int m = rooms.length;
        int n = rooms[0].length;
        Deque<int[]> q = new LinkedList<>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (rooms[i][j] == 0) {
                    q.offer(new int[] {i, j});
                }
            }
        }
        int d = 0;
        int[] dirs = {-1, 0, 1, 0, -1};
        while (!q.isEmpty()) {
            ++d;
            for (int i = q.size(); i > 0; --i) {
                int[] p = q.poll();
                for (int j = 0; j < 4; ++j) {
                    int x = p[0] + dirs[j];
                    int y = p[1] + dirs[j + 1];
                    if (x >= 0 && x < m && y >= 0 && y < n && rooms[x][y] == Integer.MAX_VALUE) {
                        rooms[x][y] = d;
                        q.offer(new int[] {x, y});
                    }
                }
            }
        }
    }
}