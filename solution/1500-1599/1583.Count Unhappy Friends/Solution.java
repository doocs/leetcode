class Solution {
    public int unhappyFriends(int n, int[][] preferences, int[][] pairs) {
        int[][] d = new int[n][n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n - 1; ++j) {
                d[i][preferences[i][j]] = j;
            }
        }
        int[] p = new int[n];
        for (var e : pairs) {
            int x = e[0], y = e[1];
            p[x] = y;
            p[y] = x;
        }
        int ans = 0;
        for (int x = 0; x < n; ++x) {
            int y = p[x];
            for (int i = 0; i < d[x][y]; ++i) {
                int u = preferences[x][i];
                int v = p[u];
                if (d[u][x] < d[u][v]) {
                    ++ans;
                    break;
                }
            }
        }
        return ans;
    }
}
