class Solution {
    private int[] p;

    public int numIslands(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        p = new int[m * n];
        for (int i = 0; i < p.length; ++i) {
            p[i] = i;
        }
        int[] dirs = {1, 0, 1};
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == '1') {
                    for (int k = 0; k < 2; ++k) {
                        int x = i + dirs[k];
                        int y = j + dirs[k + 1];
                        if (x < m && y < n && grid[x][y] == '1') {
                            p[find(x * n + y)] = find(i * n + j);
                        }
                    }
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == '1' && i * n + j == find(i * n + j)) {
                    ++ans;
                }
            }
        }
        return ans;
    }

    private int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }
}