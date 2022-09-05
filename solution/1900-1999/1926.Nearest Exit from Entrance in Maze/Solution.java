class Solution {
    public int nearestExit(char[][] maze, int[] entrance) {
        int m = maze.length;
        int n = maze[0].length;
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(entrance);
        maze[entrance[0]][entrance[1]] = '+';
        int ans = 0;
        int[] dirs = {-1, 0, 1, 0, -1};
        while (!q.isEmpty()) {
            ++ans;
            for (int k = q.size(); k > 0; --k) {
                int[] p = q.poll();
                int i = p[0], j = p[1];
                for (int l = 0; l < 4; ++l) {
                    int x = i + dirs[l], y = j + dirs[l + 1];
                    if (x >= 0 && x < m && y >= 0 && y < n && maze[x][y] == '.') {
                        if (x == 0 || x == m - 1 || y == 0 || y == n - 1) {
                            return ans;
                        }
                        q.offer(new int[] {x, y});
                        maze[x][y] = '+';
                    }
                }
            }
        }
        return -1;
    }
}