class Solution {
    private int[] nums;
    private Long[] memo;

    public long maxDifference(int[] nums) {
        this.nums = nums;
        this.memo = new Long[nums.length];
        return dfs(0);
    }

    private long dfs(int i) {
        if (i >= nums.length) {
            return 0;
        }
        if (memo[i] != null) {
            return memo[i];
        }
        int x = nums[i];
        long res = x % 3 == 0 ? x - dfs(i + 1) : x + dfs(i + 1);
        if (i + 1 < nums.length) {
            long s = x + nums[i + 1];
            long t = s % 3 == 0 ? s - dfs(i + 2) : s + dfs(i + 2);
            res = Math.max(res, t);
        }
        memo[i] = res;
        return res;
    }
}