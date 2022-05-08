class Solution {
    public int shortestPathAllKeys(String[] grid) {
        int m = grid.length, n = grid[0].length();
        int cnt = 0;
        int sx = 0, sy = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                char c = grid[i].charAt(j);
                if (Character.isLowerCase(c)) {
                    ++cnt;
                } else if (c == '@') {
                    sx = i;
                    sy = j;
                }
            }
        }
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{sx, sy, 0});
        int[] dirs = {-1, 0, 1, 0, -1};
        int ans = 0;
        int mask = (1 << cnt) - 1;
        boolean[][][] vis = new boolean[m][n][1 << cnt];
        vis[sx][sy][0] = true;
        while (!q.isEmpty()) {
            for (int t = q.size(); t > 0; --t) {
                int[] p = q.poll();
                int i = p[0], j = p[1], state = p[2];
                if (state == mask) {
                    return ans;
                }
                for (int k = 0; k < 4; ++k) {
                    int nxt = state;
                    int x = i + dirs[k], y = j + dirs[k + 1];
                    if (x >= 0 && x < m && y >= 0 && y < n) {
                        char c = grid[x].charAt(y);
                        if (c == '#' || (Character.isUpperCase(c) && (nxt & (1 << (c - 'A'))) == 0)) {
                            continue;
                        }
                        if (Character.isLowerCase(c)) {
                            nxt |= 1 << (c - 'a');
                        }
                        if (!vis[x][y][nxt]) {
                            vis[x][y][nxt] = true;
                            q.offer(new int[]{x, y, nxt});
                        }
                    }
                }
            }
            ++ans;
        }
        return -1;
    }
}