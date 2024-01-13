public class Solution {
    public int NumberOfGoodSubarraySplits(int[] nums) {
        long ans = 1, j = -1;
        int mod = 1000000007;
        int n = nums.Length;
        for (int i = 0; i < n; ++i) {
            if (nums[i] == 0) {
                continue;
            }
            if (j > -1) {
                ans = ans * (i - j) % mod;
            }
            j = i;
        }
        return j == -1 ? 0 : (int) ans;
    }
}
