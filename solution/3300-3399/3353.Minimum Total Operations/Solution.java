class Solution {
    public int minOperations(int[] nums) {
        int ans = 0;
        for (int i = 1; i < nums.length; ++i) {
            ans += nums[i] != nums[i - 1] ? 1 : 0;
        }
        return ans;
    }
}
