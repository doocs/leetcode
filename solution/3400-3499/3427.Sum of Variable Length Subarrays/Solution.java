class Solution {
    public int subarraySum(int[] nums) {
        int n = nums.length;
        int[] s = new int[n + 1];
        for (int i = 1; i <= n; ++i) {
            s[i] = s[i - 1] + nums[i - 1];
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans += s[i + 1] - s[Math.max(0, i - nums[i])];
        }
        return ans;
    }
}
