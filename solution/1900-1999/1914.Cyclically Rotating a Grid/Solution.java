class Solution {
    public int[][] rotateGrid(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        int s1 = 0, e1 = 0;
        int s2 = m - 1, e2 = n - 1;
        while (s1 <= s2 && e1 <= e2) {
            rotate(grid, s1++, e1++, s2--, e2--, k);
        }
        return grid;
    }

    private void rotate(int[][] grid, int s1, int e1, int s2, int e2, int k) {
        List<Integer> t = new ArrayList<>();
        for (int j = e2; j > e1; --j) {
            t.add(grid[s1][j]);
        }
        for (int i = s1; i < s2; ++i) {
            t.add(grid[i][e1]);
        }
        for (int j = e1; j < e2; ++j) {
            t.add(grid[s2][j]);
        }
        for (int i = s2; i > s1; --i) {
            t.add(grid[i][e2]);
        }
        int n = t.size();
        k %= n;
        if (k == 0) {
            return;
        }
        k = n - k;
        for (int j = e2; j > e1; --j) {
            grid[s1][j] = t.get(k);
            k = (k + 1) % n;
        }
        for (int i = s1; i < s2; ++i) {
            grid[i][e1] = t.get(k);
            k = (k + 1) % n;
        }
        for (int j = e1; j < e2; ++j) {
            grid[s2][j] = t.get(k);
            k = (k + 1) % n;
        }
        for (int i = s2; i > s1; --i) {
            grid[i][e2] = t.get(k);
            k = (k + 1) % n;
        }
    }
}