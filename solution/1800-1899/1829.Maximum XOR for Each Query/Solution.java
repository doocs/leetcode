class Solution {
    public int[] getMaximumXor(int[] nums, int maximumBit) {
        int n = nums.length;
        int xs = 0;
        for (int x : nums) {
            xs ^= x;
        }
        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            int x = nums[n - i - 1];
            int k = 0;
            for (int j = maximumBit - 1; j >= 0; --j) {
                if (((xs >> j) & 1) == 0) {
                    k |= 1 << j;
                }
            }
            ans[i] = k;
            xs ^= x;
        }
        return ans;
    }
}