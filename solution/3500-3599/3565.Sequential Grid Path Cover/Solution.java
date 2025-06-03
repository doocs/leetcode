class Solution {
    private int m, n;
    private long st = 0;
    private List<List<Integer>> path = new ArrayList<>();
    private final int[] dirs = {-1, 0, 1, 0, -1};

    private int f(int i, int j) {
        return i * n + j;
    }

    private boolean dfs(int i, int j, int v, int[][] grid) {
        path.add(Arrays.asList(i, j));
        if (path.size() == m * n) {
            return true;
        }
        st |= 1L << f(i, j);
        if (grid[i][j] == v) {
            v += 1;
        }
        for (int t = 0; t < 4; t++) {
            int a = dirs[t], b = dirs[t + 1];
            int x = i + a, y = j + b;
            if (0 <= x && x < m && 0 <= y && y < n && (st & (1L << f(x, y))) == 0
                && (grid[x][y] == 0 || grid[x][y] == v)) {
                if (dfs(x, y, v, grid)) {
                    return true;
                }
            }
        }
        path.remove(path.size() - 1);
        st ^= 1L << f(i, j);
        return false;
    }

    public List<List<Integer>> findPath(int[][] grid, int k) {
        m = grid.length;
        n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0 || grid[i][j] == 1) {
                    if (dfs(i, j, 1, grid)) {
                        return path;
                    }
                    path.clear();
                    st = 0;
                }
            }
        }
        return List.of();
    }
}
