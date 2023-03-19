class Solution {
    public boolean checkValidGrid(int[][] grid) {
        if (grid[0][0] != 0) {
            return false;
        }
        int n = grid.length;
        int[][] pos = new int[n * n][2];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                pos[grid[i][j]] = new int[] {i, j};
            }
        }
        for (int i = 1; i < n * n; ++i) {
            int[] p1 = pos[i - 1];
            int[] p2 = pos[i];
            int dx = Math.abs(p1[0] - p2[0]);
            int dy = Math.abs(p1[1] - p2[1]);
            boolean ok = (dx == 1 && dy == 2) || (dx == 2 && dy == 1);
            if (!ok) {
                return false;
            }
        }
        return true;
    }
}