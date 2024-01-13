class Solution {
    private int m;
    private int n;
    private int[][] grid;

    public int[][] rotateGrid(int[][] grid, int k) {
        m = grid.length;
        n = grid[0].length;
        this.grid = grid;
        for (int p = 0; p < Math.min(m, n) / 2; ++p) {
            rotate(p, k);
        }
        return grid;
    }

    private void rotate(int p, int k) {
        List<Integer> nums = new ArrayList<>();
        for (int j = p; j < n - p - 1; ++j) {
            nums.add(grid[p][j]);
        }
        for (int i = p; i < m - p - 1; ++i) {
            nums.add(grid[i][n - p - 1]);
        }
        for (int j = n - p - 1; j > p; --j) {
            nums.add(grid[m - p - 1][j]);
        }
        for (int i = m - p - 1; i > p; --i) {
            nums.add(grid[i][p]);
        }
        int l = nums.size();
        k %= l;
        if (k == 0) {
            return;
        }
        for (int j = p; j < n - p - 1; ++j) {
            grid[p][j] = nums.get(k++ % l);
        }
        for (int i = p; i < m - p - 1; ++i) {
            grid[i][n - p - 1] = nums.get(k++ % l);
        }
        for (int j = n - p - 1; j > p; --j) {
            grid[m - p - 1][j] = nums.get(k++ % l);
        }
        for (int i = m - p - 1; i > p; --i) {
            grid[i][p] = nums.get(k++ % l);
        }
    }
}