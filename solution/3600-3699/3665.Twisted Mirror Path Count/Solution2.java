class Solution {
    public int uniquePaths(int[][] grid) {
        final int mod = (int) 1e9 + 7;
        int n = grid[0].length;
        int[][] f = new int[n + 1][2];
        f[1][0] = f[1][1] = 1;
        for (int[] row : grid) {
            for (int j = 0; j < n; ++j) {
                int r = f[j][0], d = f[j + 1][1];
                if (row[j] == 1) {
                    f[j + 1][0] = d;
                    f[j + 1][1] = r;
                } else {
                    int s = (r + d) % mod;
                    f[j + 1][0] = f[j + 1][1] = s;
                }
            }
        }
        return f[n][0];
    }
}
