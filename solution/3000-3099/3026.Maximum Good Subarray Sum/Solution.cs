public class Solution {
    public long MaximumSubarraySum(int[] nums, int k) {
        Dictionary<int, long> p = new Dictionary<int, long>();
        p[nums[0]] = 0L;
        long s = 0;
        int n = nums.Length;
        long ans = long.MinValue;
        for (int i = 0; i < n; ++i) {
            s += nums[i];
            if (p.ContainsKey(nums[i] - k)) {
                ans = Math.Max(ans, s - p[nums[i] - k]);
            }
            if (p.ContainsKey(nums[i] + k)) {
                ans = Math.Max(ans, s - p[nums[i] + k]);
            }
            if (i + 1 < n && (!p.ContainsKey(nums[i + 1]) || p[nums[i + 1]] > s)) {
                p[nums[i + 1]] = s;
            }
        }
        return ans == long.MinValue ? 0 : ans;
    }
}