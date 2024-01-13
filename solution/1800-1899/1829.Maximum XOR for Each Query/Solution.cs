public class Solution {
    public int[] GetMaximumXor(int[] nums, int maximumBit) {
        int xs = 0;
        foreach (int x in nums) {
            xs ^= x;
        }
        int n = nums.Length;
        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            int x = nums[n - i - 1];
            int k = 0;
            for (int j = maximumBit - 1; j >= 0; --j) {
                if ((xs >> j & 1) == 0) {
                    k |= 1 << j;
                }
            }
            ans[i] = k;
            xs ^= x;
        }
        return ans;
    }
}
