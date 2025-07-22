class Solution {
    public int[][] minAbsDiff(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        int[][] ans = new int[m - k + 1][n - k + 1];
        for (int i = 0; i <= m - k; i++) {
            for (int j = 0; j <= n - k; j++) {
                List<Integer> nums = new ArrayList<>();
                for (int x = i; x < i + k; x++) {
                    for (int y = j; y < j + k; y++) {
                        nums.add(grid[x][y]);
                    }
                }
                Collections.sort(nums);
                int d = Integer.MAX_VALUE;
                for (int t = 1; t < nums.size(); t++) {
                    int a = nums.get(t - 1);
                    int b = nums.get(t);
                    if (a != b) {
                        d = Math.min(d, Math.abs(a - b));
                    }
                }
                ans[i][j] = (d == Integer.MAX_VALUE) ? 0 : d;
            }
        }
        return ans;
    }
}
