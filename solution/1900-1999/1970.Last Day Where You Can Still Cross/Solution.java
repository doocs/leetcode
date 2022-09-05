class Solution {
    private int[] p;
    private int row;
    private int col;
    private boolean[][] grid;
    private int[][] dirs = new int[][] {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};

    public int latestDayToCross(int row, int col, int[][] cells) {
        int n = row * col;
        this.row = row;
        this.col = col;
        p = new int[n + 2];
        for (int i = 0; i < p.length; ++i) {
            p[i] = i;
        }
        grid = new boolean[row][col];
        int top = n, bottom = n + 1;
        for (int k = cells.length - 1; k >= 0; --k) {
            int i = cells[k][0] - 1, j = cells[k][1] - 1;
            grid[i][j] = true;
            for (int[] e : dirs) {
                if (check(i + e[0], j + e[1])) {
                    p[find(i * col + j)] = find((i + e[0]) * col + j + e[1]);
                }
            }
            if (i == 0) {
                p[find(i * col + j)] = find(top);
            }
            if (i == row - 1) {
                p[find(i * col + j)] = find(bottom);
            }
            if (find(top) == find(bottom)) {
                return k;
            }
        }
        return 0;
    }

    private int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }

    private boolean check(int i, int j) {
        return i >= 0 && i < row && j >= 0 && j < col && grid[i][j];
    }
}