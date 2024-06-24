class Solution {
    private Long[][] f;
    private int[] nums;
    private int n;

    public long maximumTotalCost(int[] nums) {
        n = nums.length;
        this.nums = nums;
        f = new Long[n][2];
        return dfs(0, 0);
    }

    private long dfs(int i, int j) {
        if (i >= n) {
            return 0;
        }
        if (f[i][j] != null) {
            return f[i][j];
        }
        f[i][j] = nums[i] + dfs(i + 1, 1);
        if (j == 1) {
            f[i][j] = Math.max(f[i][j], -nums[i] + dfs(i + 1, 0));
        }
        return f[i][j];
    }
}