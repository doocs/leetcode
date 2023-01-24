public class Solution {
    public int[] GetMaximumXor(int[] nums, int maximumBit) {
        int xs = 0;
        foreach (int x in nums) {
            xs ^= x;
        }
        int mask = (1 << maximumBit) - 1;
        int n = nums.Length;
        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            int x = nums[n - i - 1];
            int k = xs ^ mask;
            ans[i] = k;
            xs ^= x;
        }
        return ans;
    }
}