public class Solution {
    public int MinSubArrayLen(int target, int[] nums) {
        int n = nums.Length;
        long s = 0;
        int ans = n + 1;
        for (int i = 0, j = 0; i < n; ++i) {
            s += nums[i];
            while (s >= target) {
                ans = Math.Min(ans, i - j + 1);
                s -= nums[j++];
            }
        }
        return ans == n + 1 ? 0 : ans;
    }
}