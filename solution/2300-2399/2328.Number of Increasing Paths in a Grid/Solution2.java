class Solution {
    public int countPaths(int[][] grid) {
        final int mod = (int) 1e9 + 7;
        int m = grid.length, n = grid[0].length;
        int[][] f = new int[m][n];
        int[][] cells = new int[m * n][3];
        for (int i = 0, k = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                f[i][j] = 1;
                cells[k][0] = grid[i][j];
                cells[k][1] = i;
                cells[k][2] = j;
                ++k;
            }
        }
        Arrays.sort(cells, (a, b) -> Integer.compare(b[0], a[0]));
        int[] dirs = {-1, 0, 1, 0, -1};
        for (int[] cell : cells) {
            int i = cell[1], j = cell[2];
            for (int k = 0; k < 4; ++k) {
                int x = i + dirs[k], y = j + dirs[k + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && grid[i][j] < grid[x][y]) {
                    f[i][j] = (f[i][j] + f[x][y]) % mod;
                }
            }
        }
        long ans = 0;
        for (int[] row : f) {
            for (int v : row) {
                ans += v;
            }
        }
        return (int) (ans % mod);
    }
}
