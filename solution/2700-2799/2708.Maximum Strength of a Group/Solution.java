class Solution {
    public long maxStrength(int[] nums) {
        long ans = (long) -1e14;
        int n = nums.length;
        for (int i = 1; i < 1 << n; ++i) {
            long t = 1;
            for (int j = 0; j < n; ++j) {
                if ((i >> j & 1) == 1) {
                    t *= nums[j];
                }
            }
            ans = Math.max(ans, t);
        }
        return ans;
    }
}