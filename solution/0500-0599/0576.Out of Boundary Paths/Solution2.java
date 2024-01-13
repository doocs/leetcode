class Solution {
    public int findPaths(int m, int n, int N, int i, int j) {
        final int MOD = (int) (1e9 + 7);
        final int[] dirs = new int[] {-1, 0, 1, 0, -1};
        int[][] f = new int[m][n];
        f[i][j] = 1;
        int res = 0;
        for (int step = 0; step < N; ++step) {
            int[][] temp = new int[m][n];
            for (int x = 0; x < m; ++x) {
                for (int y = 0; y < n; ++y) {
                    for (int k = 0; k < 4; ++k) {
                        int tx = x + dirs[k], ty = y + dirs[k + 1];
                        if (tx >= 0 && tx < m && ty >= 0 && ty < n) {
                            temp[tx][ty] += f[x][y];
                            temp[tx][ty] %= MOD;
                        } else {
                            res += f[x][y];
                            res %= MOD;
                        }
                    }
                }
            }
            f = temp;
        }
        return res;
    }
}