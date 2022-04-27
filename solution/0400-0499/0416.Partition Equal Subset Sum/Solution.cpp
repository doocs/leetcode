class Solution {
public:
    bool canPartition(vector<int>& nums) {
        int s = accumulate(nums.begin(), nums.end(), 0);
        if (s % 2 != 0) return false;
        int n = s >> 1;
        vector<bool> dp(n + 1);
        dp[0] = true;
        for (int& v : nums)
            for (int j = n; j >= v; --j)
                dp[j] = dp[j] || dp[j - v];
        return dp[n];
    }
};