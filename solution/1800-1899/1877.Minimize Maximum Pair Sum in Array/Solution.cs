public class Solution {
    public int MinPairSum(int[] nums) {
        Array.Sort(nums);
        int ans = 0, n = nums.Length;
        for (int i = 0; i < n >> 1; ++i) {
            ans = Math.Max(ans, nums[i] + nums[n - i - 1]);
        }
        return ans;
    }
}
