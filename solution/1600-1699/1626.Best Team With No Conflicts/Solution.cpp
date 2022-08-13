class Solution {
public:
    int bestTeamScore(vector<int>& scores, vector<int>& ages) {
        int n = ages.size();
        vector<vector<int>> nums(n);
        for (int i = 0; i < n; ++i) nums[i] = {ages[i], scores[i]};
        sort(nums.begin(), nums.end());
        vector<int> dp(n);
        for (int i = 0; i < n; ++i) {
            dp[i] = nums[i][1];
            for (int j = 0; j < i; ++j) {
                if (nums[i][1] >= nums[j][1])
                    dp[i] = max(dp[i], dp[j] + nums[i][1]);
            }
        }
        return *max_element(dp.begin(), dp.end());
    }
};