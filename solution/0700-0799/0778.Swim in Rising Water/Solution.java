class Solution {
    private int[] p;

    public int swimInWater(int[][] grid) {
        int n = grid.length;
        p = new int[n * n];
        for (int i = 0; i < p.length; ++i) {
            p[i] = i;
        }
        int[] hi = new int[n * n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                hi[grid[i][j]] = i * n + j;
            }
        }
        int[] dirs = {-1, 0, 1, 0, -1};
        for (int t = 0; t < n * n; ++t) {
            int i = hi[t] / n;
            int j = hi[t] % n;
            for (int k = 0; k < 4; ++k) {
                int x = i + dirs[k];
                int y = j + dirs[k + 1];
                if (x >= 0 && x < n && y >= 0 && y < n && grid[x][y] <= t) {
                    p[find(x * n + y)] = find(i * n + j);
                }
                if (find(0) == find(n * n - 1)) {
                    return t;
                }
            }
        }
        return -1;
    }

    private int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }
}