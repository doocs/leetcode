class Solution {
    public int extractMantra(String[] matrix, String mantra) {
        int m = matrix.length, n = matrix[0].length();
        int l = mantra.length();
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {0, 0, 0});
        boolean[][][] vis = new boolean[m][n][l + 1];
        vis[0][0][0] = true;
        int[] dirs = {-1, 0, 1, 0, -1};
        int ans = 0;
        for (; !q.isEmpty(); ++ans) {
            for (int size = q.size(); size > 0; --size) {
                var p = q.poll();
                int i = p[0], j = p[1], k = p[2];
                if (k == l) {
                    return ans;
                }
                if (matrix[i].charAt(j) == mantra.charAt(k) && !vis[i][j][k + 1]) {
                    vis[i][j][k + 1] = true;
                    q.offer(new int[] {i, j, k + 1});
                } else {
                    for (int c = 0; c < 4; ++c) {
                        int x = i + dirs[c], y = j + dirs[c + 1];
                        if (x >= 0 && x < m && y >= 0 && y < n && !vis[x][y][k]) {
                            vis[x][y][k] = true;
                            q.offer(new int[] {x, y, k});
                        }
                    }
                }
            }
        }
        return -1;
    }
}