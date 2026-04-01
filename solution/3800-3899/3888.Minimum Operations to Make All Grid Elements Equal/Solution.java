class Solution {
    int[][] grid;
    int m, n, k;

    public long minOperations(int[][] grid, int k) {
        this.grid = grid;
        this.k = k;
        this.m = grid.length;
        this.n = grid[0].length;

        int mx = Integer.MIN_VALUE;
        for (int[] row : grid) {
            for (int v : row) {
                mx = Math.max(mx, v);
            }
        }

        for (int t = mx; t <= mx + 1; t++) {
            long res = check(t);
            if (res != -1) {
                return res;
            }
        }
        return -1;
    }

    private long check(int target) {
        long[][] diff = new long[m + 2][n + 2];
        long totalOps = 0;

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                diff[i][j] += diff[i - 1][j] + diff[i][j - 1] - diff[i - 1][j - 1];
                long cur = grid[i - 1][j - 1] + diff[i][j];

                if (cur > target) {
                    return -1;
                }

                if (cur < target) {
                    if (i + k - 1 > m || j + k - 1 > n) {
                        return -1;
                    }

                    long need = target - cur;
                    totalOps += need;

                    diff[i][j] += need;
                    diff[i + k][j] -= need;
                    diff[i][j + k] -= need;
                    diff[i + k][j + k] += need;
                }
            }
        }
        return totalOps;
    }
}
