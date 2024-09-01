class Solution {
    public int maxScore(List<List<Integer>> grid) {
        int m = grid.size();
        int mx = 0;
        boolean[][] g = new boolean[101][m + 1];
        for (int i = 0; i < m; ++i) {
            for (int x : grid.get(i)) {
                g[x][i] = true;
                mx = Math.max(mx, x);
            }
        }
        int[][] f = new int[mx + 1][1 << m];
        for (int i = 1; i <= mx; ++i) {
            for (int j = 0; j < 1 << m; ++j) {
                f[i][j] = f[i - 1][j];
                for (int k = 0; k < m; ++k) {
                    if (g[i][k] && (j >> k & 1) == 1) {
                        f[i][j] = Math.max(f[i][j], f[i - 1][j ^ 1 << k] + i);
                    }
                }
            }
        }
        return f[mx][(1 << m) - 1];
    }
}
