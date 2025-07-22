class NeighborSum {
    private int[][] grid;
    private final Map<Integer, int[]> d = new HashMap<>();
    private final int[][] dirs = {{-1, 0, 1, 0, -1}, {-1, 1, 1, -1, -1}};

    public NeighborSum(int[][] grid) {
        this.grid = grid;
        int m = grid.length, n = grid[0].length;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                d.put(grid[i][j], new int[] {i, j});
            }
        }
    }

    public int adjacentSum(int value) {
        return cal(value, 0);
    }

    public int diagonalSum(int value) {
        return cal(value, 1);
    }

    private int cal(int value, int k) {
        int[] p = d.get(value);
        int s = 0;
        for (int q = 0; q < 4; ++q) {
            int x = p[0] + dirs[k][q], y = p[1] + dirs[k][q + 1];
            if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length) {
                s += grid[x][y];
            }
        }
        return s;
    }
}

/**
 * Your NeighborSum object will be instantiated and called as such:
 * NeighborSum obj = new NeighborSum(grid);
 * int param_1 = obj.adjacentSum(value);
 * int param_2 = obj.diagonalSum(value);
 */
