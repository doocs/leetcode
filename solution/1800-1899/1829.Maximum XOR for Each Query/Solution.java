class Solution {
    public int[] getMaximumXor(int[] nums, int maximumBit) {
        int n = nums.length;
        int[] s = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] ^ nums[i];
        }
        int[] ans = new int[n];
        for (int i = n; i > 0; --i) {
            int t = 0, x = s[i];
            for (int j = 0; j < maximumBit; ++j) {
                if (((x >> j) & 1) == 0) {
                    t |= (1 << j);
                }
            }
            ans[n - i] = t;
        }
        return ans;
    }
}