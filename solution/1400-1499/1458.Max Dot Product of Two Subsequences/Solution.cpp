class Solution {
public:
    int maxDotProduct(vector<int>& nums1, vector<int>& nums2) {
        int m = nums1.size(), n = nums2.size();
        vector<vector<int>> dp(m + 1, vector<int>(n + 1, INT_MIN));
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                int v = nums1[i - 1] * nums2[j - 1];
                dp[i][j] = max(dp[i - 1][j], dp[i][j - 1]);
                dp[i][j] = max(dp[i][j], max(0, dp[i - 1][j - 1]) + v);
            }
        }
        return dp[m][n];
    }
};