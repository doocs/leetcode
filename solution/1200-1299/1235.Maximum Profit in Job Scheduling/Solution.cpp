class Solution {
public:
    int jobScheduling(vector<int>& startTime, vector<int>& endTime, vector<int>& profit) {
        int n = profit.size();
        vector<tuple<int, int, int>> jobs(n);
        for (int i = 0; i < n; ++i) jobs[i] = {endTime[i], startTime[i], profit[i]};
        sort(jobs.begin(), jobs.end());
        vector<int> dp(n + 1);
        for (int i = 0; i < n; ++i) {
            auto [_, s, p] = jobs[i];
            int j = upper_bound(jobs.begin(), jobs.begin() + i, s, [&](int x, auto& job) -> bool { return x < get<0>(job); }) - jobs.begin();
            dp[i + 1] = max(dp[i], dp[j] + p);
        }
        return dp[n];
    }
};