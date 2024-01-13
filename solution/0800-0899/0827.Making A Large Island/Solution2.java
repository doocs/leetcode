class Solution {
    private int n;
    private int ans;
    private int root;
    private int[][] p;
    private int[][] grid;
    private int[] cnt;
    private int[] dirs = new int[] {-1, 0, 1, 0, -1};

    public int largestIsland(int[][] grid) {
        n = grid.length;
        cnt = new int[n * n + 1];
        p = new int[n][n];
        this.grid = grid;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 1 && p[i][j] == 0) {
                    ++root;
                    dfs(i, j);
                }
            }
        }
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 0) {
                    int t = 1;
                    Set<Integer> vis = new HashSet<>();
                    for (int k = 0; k < 4; ++k) {
                        int x = i + dirs[k], y = j + dirs[k + 1];
                        if (x >= 0 && x < n && y >= 0 && y < n) {
                            int root = p[x][y];
                            if (!vis.contains(root)) {
                                vis.add(root);
                                t += cnt[root];
                            }
                        }
                    }
                    ans = Math.max(ans, t);
                }
            }
        }
        return ans;
    }

    private void dfs(int i, int j) {
        p[i][j] = root;
        ++cnt[root];
        ans = Math.max(ans, cnt[root]);
        for (int k = 0; k < 4; ++k) {
            int x = i + dirs[k], y = j + dirs[k + 1];
            if (x >= 0 && x < n && y >= 0 && y < n && grid[x][y] == 1 && p[x][y] == 0) {
                dfs(x, y);
            }
        }
    }
}