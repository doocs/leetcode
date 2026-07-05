class Solution {
    public int maxValidPairSum(int[] nums, int k) {
        int ans = 0;
        int x = 0;
        for (int j = k; j < nums.length; ++j) {
            int y = nums[j];
            x = Math.max(x, nums[j - k]);
            ans = Math.max(ans, x + y);
        }
        return ans;
    }
}