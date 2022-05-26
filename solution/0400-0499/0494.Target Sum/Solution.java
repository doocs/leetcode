class Solution {
    public int findTargetSumWays(int[] nums, int S) {
        int[] ans = new int[1];
        wayDfs(nums, 0, S, ans);
        return ans[0];
    }

    private void wayDfs(int[] nums, int start, int left, int[] ans) {
        if (start == nums.length) {
            if (left == 0) ans[0]++;
            return;
        }

        wayDfs(nums, start + 1, left + nums[start], ans);
        wayDfs(nums, start + 1, left - nums[start], ans);
    }
}