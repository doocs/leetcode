class Solution {
    public int countPartitions(int[] nums) {
        int l = 0, r = 0;
        for (int x : nums) {
            r += x;
        }
        int ans = 0;
        for (int i = 0; i < nums.length - 1; ++i) {
            l += nums[i];
            r -= nums[i];
            if ((l - r) % 2 == 0) {
                ++ans;
            }
        }
        return ans;
    }
}
