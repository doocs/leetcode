class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        final int inf = 1 << 30;
        int ans = inf;
        int s = 0;
        for (int i = 0, j = 0; j < nums.length; ++j) {
            s += nums[j];
            while (s >= target) {
                ans = Math.min(ans, j - i + 1);
                s -= nums[i++];
            }
        }
        return ans == inf ? 0 : ans;
    }
}