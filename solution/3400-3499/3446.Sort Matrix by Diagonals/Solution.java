class Solution {
    public int[][] sortMatrix(int[][] grid) {
        int n = grid.length;
        for (int k = n - 2; k >= 0; --k) {
            int i = k, j = 0;
            List<Integer> t = new ArrayList<>();
            while (i < n && j < n) {
                t.add(grid[i++][j++]);
            }
            Collections.sort(t);
            for (int x : t) {
                grid[--i][--j] = x;
            }
        }
        for (int k = n - 2; k > 0; --k) {
            int i = k, j = n - 1;
            List<Integer> t = new ArrayList<>();
            while (i >= 0 && j >= 0) {
                t.add(grid[i--][j--]);
            }
            Collections.sort(t);
            for (int x : t) {
                grid[++i][++j] = x;
            }
        }
        return grid;
    }
}
