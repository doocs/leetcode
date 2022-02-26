class Solution {
    public int maximumDifference(int[] nums) {
        int mi = nums[0];
        int ans = -1;
        for (int i = 1; i < nums.length; ++i) {
            if (nums[i] > mi) {
                ans = Math.max(ans, nums[i] - mi);
            } else {
                mi = nums[i];
            }
        }
        return ans;
    }
}