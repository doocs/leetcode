class Solution {
public:
    int findTargetSumWays(vector<int>& nums, int target) {
        int s = 0;
        for (int& v : nums) s += v;
        if (s < target || (s - target) % 2 != 0) return 0;
        int m = nums.size(), n = (s - target) / 2;
        vector<int> dp(n + 1);
        dp[0] = 1;
        for (int i = 1; i <= m; ++i)
        {
            for (int j = n; j >= nums[i - 1]; --j)
            {
                dp[j] += dp[j - nums[i - 1]];
            }
        }
        return dp[n];
    }
};