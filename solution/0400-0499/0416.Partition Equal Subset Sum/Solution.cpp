class Solution {
public:
    bool canPartition(vector<int>& nums) {
        int s = 0;
        for (int& v : nums) s += v;
        if (s % 2 != 0) return false;
        int m = nums.size(), n = s >> 1;
        vector<bool> dp(n + 1);
        dp[0] = true;
        for (int i = 1; i <= m; ++i)
            for (int j = n; j >= nums[i - 1]; --j)
                dp[j] = dp[j] || dp[j - nums[i - 1]];
        return dp[n];
    }
};