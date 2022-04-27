class Solution {
public:
    int findTargetSumWays(vector<int>& nums, int target) {
        int s = accumulate(nums.begin(), nums.end(), 0);
        if (s < target || (s - target) % 2 != 0) return 0;
        int n = (s - target) / 2;
        vector<int> dp(n + 1);
        dp[0] = 1;
        for (int& v : nums)
            for (int j = n; j >= v; --j)
                dp[j] += dp[j - v];
        return dp[n];
    }
};