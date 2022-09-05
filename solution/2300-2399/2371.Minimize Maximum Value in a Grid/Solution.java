class Solution {
    public int[][] minScore(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        List<int[]> nums = new ArrayList<>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                nums.add(new int[] {grid[i][j], i, j});
            }
        }
        Collections.sort(nums, (a, b) -> a[0] - b[0]);
        int[] rowMax = new int[m];
        int[] colMax = new int[n];
        int[][] ans = new int[m][n];
        for (int[] num : nums) {
            int i = num[1], j = num[2];
            ans[i][j] = Math.max(rowMax[i], colMax[j]) + 1;
            rowMax[i] = ans[i][j];
            colMax[j] = ans[i][j];
        }
        return ans;
    }
}