class Solution {
    private Integer[] f;
    private int[] nums;
    private int n;
    private int target;

    public int maximumJumps(int[] nums, int target) {
        n = nums.length;
        this.target = target;
        this.nums = nums;
        f = new Integer[n];
        int ans = dfs(0);
        return ans < 0 ? -1 : ans;
    }

    private int dfs(int i) {
        if (i == n - 1) {
            return 0;
        }
        if (f[i] != null) {
            return f[i];
        }
        int ans = -(1 << 30);
        for (int j = i + 1; j < n; ++j) {
            if (Math.abs(nums[i] - nums[j]) <= target) {
                ans = Math.max(ans, 1 + dfs(j));
            }
        }
        return f[i] = ans;
    }
}