class Solution {
    public int minCost(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        int inf = Integer.MAX_VALUE / 2;

        int[][][] f = new int[k + 1][m][n];
        for (int t = 0; t <= k; t++) {
            for (int i = 0; i < m; i++) {
                Arrays.fill(f[t][i], inf);
            }
        }

        f[0][0][0] = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i > 0) {
                    f[0][i][j] = Math.min(f[0][i][j], f[0][i - 1][j] + grid[i][j]);
                }
                if (j > 0) {
                    f[0][i][j] = Math.min(f[0][i][j], f[0][i][j - 1] + grid[i][j]);
                }
            }
        }

        Map<Integer, List<int[]>> g = new HashMap<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int x = grid[i][j];
                g.computeIfAbsent(x, z -> new ArrayList<>()).add(new int[] {i, j});
            }
        }

        List<Integer> keys = new ArrayList<>(g.keySet());
        keys.sort(Collections.reverseOrder());

        for (int t = 1; t <= k; t++) {
            int mn = inf;
            for (int key : keys) {
                List<int[]> pos = g.get(key);
                for (int[] p : pos) {
                    mn = Math.min(mn, f[t - 1][p[0]][p[1]]);
                }
                for (int[] p : pos) {
                    f[t][p[0]][p[1]] = mn;
                }
            }
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (i > 0) {
                        f[t][i][j] = Math.min(f[t][i][j], f[t][i - 1][j] + grid[i][j]);
                    }
                    if (j > 0) {
                        f[t][i][j] = Math.min(f[t][i][j], f[t][i][j - 1] + grid[i][j]);
                    }
                }
            }
        }

        int ans = inf;
        for (int t = 0; t <= k; t++) {
            ans = Math.min(ans, f[t][m - 1][n - 1]);
        }
        return ans;
    }
}
