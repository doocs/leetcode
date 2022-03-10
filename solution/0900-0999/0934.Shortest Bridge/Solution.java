class Solution {
    private int[][] grid;
    private int[] dirs = {-1, 0, 1, 0, -1};
    private int m;
    private int n;

    public int shortestBridge(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        this.grid = grid;
        int[] start = find();
        Queue<int[]> q = new LinkedList<>();
        dfs(start[0], start[1], q);
        int ans = -1;
        while (!q.isEmpty()) {
            ++ans;
            for (int k = q.size(); k > 0; --k) {
                int[] p = q.poll();
                for (int i = 0; i < 4; ++i) {
                    int x = p[0] + dirs[i];
                    int y = p[1] + dirs[i + 1];
                    if (x >= 0 && x < m && y >= 0 && y < n) {
                        if (grid[x][y] == 1) {
                            return ans;
                        }
                        if (grid[x][y] == 0) {
                            grid[x][y] = 2;
                            q.offer(new int[]{x, y});
                        }
                    }
                }
            }
        }
        return 0;
    }

    private void dfs(int i, int j, Queue<int[]> q) {
        grid[i][j] = 2;
        q.offer(new int[]{i, j});
        for (int k = 0; k < 4; ++k) {
            int x = i + dirs[k];
            int y = j + dirs[k + 1];
            if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1) {
                dfs(x, y, q);
            }
        }
    }

    private int[] find() {
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 1) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{0, 0};
    }
}