class Solution {
public:
    bool canPartition(vector<int>& nums) {
        int s = 0;
        for (int x : nums) s += x;
        if (s % 2 != 0) return false;
        int m = nums.size(), n = (s >> 1) + 1;
        vector<bool> dp(n);
        dp[0] = true;
        if (nums[0] < n) dp[nums[0]] = true;
        for (int i = 1; i < m; ++i) {
            for (int j = n - 1; j >= nums[i]; --j) {
                dp[j] = dp[j] || dp[j - nums[i]];
            }
        }
        return dp[n - 1];
    }
};