class Solution {
    private int[] p;
    private int[] size;
    private int[][] g;
    private int m;
    private int n;
    private int[][] dirs = new int[][]{{0, -1}, {0, 1}, {1, 0}, {-1, 0}};

    public int[] hitBricks(int[][] grid, int[][] hits) {
        m = grid.length;
        n = grid[0].length;
        p = new int[m * n + 1];
        size = new int[m * n + 1];
        for (int i = 0; i < p.length; ++i) {
            p[i] = i;
            size[i] = 1;
        }
        g = new int[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                g[i][j] = grid[i][j];
            }
        }
        for (int[] e : hits) {
            g[e[0]][e[1]] = 0;
        }
        for (int j = 0; j < n; ++j) {
            if (g[0][j] == 1) {
                union(j, m * n);
            }
        }
        for (int i = 1; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (g[i][j] == 0) {
                    continue;
                }
                if (g[i - 1][j] == 1) {
                    union(i * n + j, (i - 1) * n + j);
                }
                if (j > 0 && g[i][j - 1] == 1) {
                    union(i * n + j, i * n + j - 1);
                }
            }
        }
        int[] res = new int[hits.length];
        for (int k = hits.length - 1; k >= 0; --k) {
            int i = hits[k][0], j = hits[k][1];
            if (grid[i][j] == 0) {
                continue;
            }
            int origin = size[find(m * n)];
            if (i == 0) {
                union(j, m * n);
            }
            for (int[] e : dirs) {
                if (check(i + e[0], j + e[1])) {
                    union(i * n + j, (i + e[0]) * n + j + e[1]);
                }
            }
            int cur = size[find(m * n)];
            res[k] = Math.max(0, cur - origin - 1);
            g[i][j] = 1;
        }
        return res;
    }

    private int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }

    private void union(int a, int b) {
        int pa = find(a), pb = find(b);
        if (pa != pb) {
            size[pb] += size[pa];
            p[pa] = pb;
        }
    }

    private boolean check(int i, int j) {
        return i >= 0 && i < m && j >= 0 && j < n && g[i][j] == 1;
    }
}