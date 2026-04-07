class Solution {
    public boolean canPartitionGrid(int[][] grid) {
        return check(grid) || check(rotate(grid));
    }

    private boolean check(int[][] g) {
        int m = g.length, n = g[0].length;
        long s1 = 0, s2 = 0;

        Map<Long, Integer> cnt1 = new HashMap<>();
        Map<Long, Integer> cnt2 = new HashMap<>();

        for (int[] row : g) {
            for (int x : row) {
                s2 += x;
                cnt2.merge((long) x, 1, Integer::sum);
            }
        }

        for (int i = 0; i < m - 1; i++) {
            for (int x : g[i]) {
                s1 += x;
                s2 -= x;

                cnt1.merge((long) x, 1, Integer::sum);
                cnt2.merge((long) x, -1, Integer::sum);
            }

            if (s1 == s2) {
                return true;
            }

            if (s1 < s2) {
                long diff = s2 - s1;
                if (cnt2.getOrDefault(diff, 0) > 0) {
                    if ((m - i - 1 > 1 && n > 1)
                        || (i == m - 2 && (g[i + 1][0] == diff || g[i + 1][n - 1] == diff))
                        || (n == 1 && (g[i + 1][0] == diff || g[m - 1][0] == diff))) {
                        return true;
                    }
                }
            } else {
                long diff = s1 - s2;
                if (cnt1.getOrDefault(diff, 0) > 0) {
                    if ((i + 1 > 1 && n > 1) || (i == 0 && (g[0][0] == diff || g[0][n - 1] == diff))
                        || (n == 1 && (g[0][0] == diff || g[i][0] == diff))) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private int[][] rotate(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] t = new int[n][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                t[j][i] = grid[i][j];
            }
        }
        return t;
    }
}
