class Solution {
    private int[] dirs = {-1, 0, 1, 0, -1};

    public int shortestPathAllKeys(String[] grid) {
        int m = grid.length, n = grid[0].length();
        int k = 0;
        int si = 0, sj = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                char c = grid[i].charAt(j);
                if (Character.isLowerCase(c)) {
                    // 累加钥匙数量
                    ++k;
                } else if (c == '@') {
                    // 起点
                    si = i;
                    sj = j;
                }
            }
        }
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {si, sj, 0});
        boolean[][][] vis = new boolean[m][n][1 << k];
        vis[si][sj][0] = true;
        int ans = 0;
        while (!q.isEmpty()) {
            for (int t = q.size(); t > 0; --t) {
                var p = q.poll();
                int i = p[0], j = p[1], state = p[2];
                // 找到所有钥匙，返回当前步数
                if (state == (1 << k) - 1) {
                    return ans;
                }
                // 往四个方向搜索
                for (int h = 0; h < 4; ++h) {
                    int x = i + dirs[h], y = j + dirs[h + 1];
                    // 在边界范围内
                    if (x >= 0 && x < m && y >= 0 && y < n) {
                        char c = grid[x].charAt(y);
                        // 是墙，或者是锁，但此时没有对应的钥匙，无法通过
                        if (c == '#'
                            || (Character.isUpperCase(c) && ((state >> (c - 'A')) & 1) == 0)) {
                            continue;
                        }
                        int nxt = state;
                        // 是钥匙
                        if (Character.isLowerCase(c)) {
                            // 更新状态
                            nxt |= 1 << (c - 'a');
                        }
                        // 此状态未访问过，入队
                        if (!vis[x][y][nxt]) {
                            vis[x][y][nxt] = true;
                            q.offer(new int[] {x, y, nxt});
                        }
                    }
                }
            }
            // 步数加一
            ++ans;
        }
        return -1;
    }
}