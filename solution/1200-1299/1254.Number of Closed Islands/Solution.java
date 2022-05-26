class Solution {
    private int[] p;

    public int closedIsland(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        p = new int[m * n];
        for (int i = 0; i < m * n; ++i) {
            p[i] = i;
        }
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 1) {
                    continue;
                }
                int idx = i * n + j;
                if (i < m - 1 && grid[i + 1][j] == 0) {
                    p[find(idx)] = find((i + 1) * n + j);
                }
                if (j < n - 1 && grid[i][j + 1] == 0) {
                    p[find(idx)] = find(i * n + j + 1);
                }
            }
        }
        boolean[] s = new boolean[m * n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 0) {
                    s[find(i * n + j)] = true;
                }
            }
        }
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                int root = find(i * n + j);
                if (!s[root]) {
                    continue;
                }
                if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
                    s[root] = false;
                }
            }
        }
        int res = 0;
        for (int i = 0; i < m * n; ++i) {
            if (s[i]) {
                ++res;
            }
        }
        return res;
    }

    private int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }
}