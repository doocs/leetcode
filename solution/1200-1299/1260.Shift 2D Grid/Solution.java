class Solution {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        k %= (m * n);
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < m; ++i) {
            List<Integer> t = new ArrayList<>();
            for (int j = 0; j < n; ++j) {
                t.add(0);
            }
            ans.add(t);
        }
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                int t = (i * n + j + k) % (m * n);
                ans.get(t / n).set(t % n, grid[i][j]);
            }
        }
        return ans;
    }
}