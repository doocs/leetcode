class Solution {
public:
    int bestTeamScore(vector<int> &scores, vector<int> &ages) {
        vector<pair<int, int>> nums;
        int n = ages.size();
        for (int i = 0; i < n; ++i) nums.push_back({scores[i], ages[i]});
        sort(nums.begin(), nums.end(), [](auto &a, auto &b) {
            return a.second == b.second ? a.first < b.first : a.second < b.second;
        });
        vector<int> dp(n);
        int res = 0;
        for (int i = 0; i < n; ++i)
        {
            dp[i] = nums[i].first;
            for (int j = 0; j < i; ++j)
            {
                if (nums[j].first <= nums[i].first)
                    dp[i] = max(dp[i], dp[j] + nums[i].first);
            }
            res = max(res, dp[i]);
        }
        return res;
    }
};