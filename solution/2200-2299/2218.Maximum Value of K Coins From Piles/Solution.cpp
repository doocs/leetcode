class Solution {
public:
    int maxValueOfCoins(vector<vector<int>>& piles, int k) {
        vector<vector<int>> presum;
        for (auto& p : piles) {
            int m = p.size();
            vector<int> s(m + 1);
            for (int i = 0; i < m; ++i) s[i + 1] = s[i] + p[i];
            presum.push_back(s);
        }
        vector<int> dp(k + 1);
        for (auto& s : presum) {
            for (int j = k; ~j; --j) {
                for (int idx = 0; idx < s.size(); ++idx) {
                    if (j >= idx) dp[j] = max(dp[j], dp[j - idx] + s[idx]);
                }
            }
        }
        return dp[k];
    }
};