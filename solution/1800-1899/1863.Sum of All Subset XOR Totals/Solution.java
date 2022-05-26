class Solution {
    private int res;

    public int subsetXORSum(int[] nums) {
        dfs(nums, 0, 0);
        return res;
    }

    private void dfs(int[] nums, int depth, int prev) {
        res += prev;
        for (int i = depth; i < nums.length; ++i) {
            prev ^= nums[i];
            dfs(nums, ++depth, prev);
            prev ^= nums[i];
        }
    }
}