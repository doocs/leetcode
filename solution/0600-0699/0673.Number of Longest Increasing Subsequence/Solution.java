class Solution {
    public int findNumberOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int[] dp = new int[n];
        int[] f = new int[n];
        Arrays.fill(dp, 1);
        Arrays.fill(f, 1);
        int max = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                if (nums[i] > nums[j]) {
                    if (dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j] + 1;
                        f[i] = f[j];
                    } else if (dp[j] + 1 == dp[i]) {
                        f[i] += f[j];
                    }
                }
            }
            max = Math.max(max, dp[i]);
        }
        int res = 0;
        for (int i = 0; i < n; ++i) {
            if (dp[i] == max) {
                res += f[i];
            }
        }
        return res;
    }
}
