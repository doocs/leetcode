class Solution {
public:
    int longestArithSeqLength(vector<int>& nums) {
        int n = nums.size();
        int ans = 0;
        vector<vector<int>> dp(n, vector<int>(1001, 1));
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                int d = nums[i] - nums[j] + 500;
                dp[i][d] = max(dp[i][d], dp[j][d] + 1);
                ans = max(ans, dp[i][d]);
            }
        }
        return ans;
    }
};