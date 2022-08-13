class Solution {
public:
    int maximumProfit(vector<int>& present, vector<int>& future, int budget) {
        int n = present.size();
        vector<int> dp(budget + 1);
        for (int i = 0; i < n; i++) {
            for (int j = budget; j >= present[i]; j--) {
                dp[j] = max(dp[j], dp[j - present[i]] + future[i] - present[i]);
            }
        }
        return dp.back();
    }
};