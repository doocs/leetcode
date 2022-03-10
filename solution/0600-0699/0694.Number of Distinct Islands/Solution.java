class Solution {
    private int m;
    private int n;
    private int[][] grid;

    public int numDistinctIslands(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        this.grid = grid;
        Set<String> paths = new HashSet<>();
        
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 1) {
                    StringBuilder path = new StringBuilder();
                    dfs(i, j, 0, path);
                    paths.add(path.toString());
                }
            }
        }
        return paths.size();
    }

    private void dfs(int i, int j, int direction, StringBuilder path) {
        grid[i][j] = 0;
        path.append(direction);
        int[] dirs = {-1, 0, 1, 0, -1};
        for (int k = 1; k < 5; ++k) {
            int x = i + dirs[k - 1];
            int y = j + dirs[k];
            if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1) {
                dfs(x, y, k, path);
            }
        }
        path.append(direction);
    }
}