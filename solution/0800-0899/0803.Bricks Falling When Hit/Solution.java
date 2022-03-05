class Solution {
    private int[] p;
    private int[] size;

    public int[] hitBricks(int[][] grid, int[][] hits) {
        int m = grid.length;
        int n = grid[0].length;
        p = new int[m * n + 1];
        size = new int[m * n + 1];
        for (int i = 0; i < p.length; ++i) {
            p[i] = i;
            size[i] = 1;
        }
        int[][] g = new int[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                g[i][j] = grid[i][j];
            }
        }
        for (int[] h : hits) {
            g[h[0]][h[1]] = 0;
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
        int[] ans = new int[hits.length];
        int[] dirs = {-1, 0, 1, 0, -1};
        for (int k = hits.length - 1; k >= 0; --k) {
            int i = hits[k][0];
            int j = hits[k][1];
            if (grid[i][j] == 0) {
                continue;
            }
            g[i][j] = 1;
            int prev = size[find(m * n)];
            if (i == 0) {
                union(j, m * n);
            }
            for (int l = 0; l < 4; ++l) {
                int x = i + dirs[l];
                int y = j + dirs[l + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && g[x][y] == 1) {
                    union(i * n + j, x * n + y);
                }
            }
            int curr = size[find(m * n)];
            ans[k] = Math.max(0, curr - prev - 1);
        }
        return ans;
    }

    private int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }

    private void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);
        if (pa != pb) {
            size[pb] += size[pa];
            p[pa] = pb;
        }
    }
}