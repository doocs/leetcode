class Solution {
public:
    int constrainedSubsetSum(vector<int>& nums, int k) {
        int n = nums.size();
        vector<int> dp(n);
        int ans = INT_MIN;
        deque<int> q;
        for (int i = 0; i < n; ++i) {
            if (!q.empty() && i - q.front() > k) q.pop_front();
            dp[i] = max(0, q.empty() ? 0 : dp[q.front()]) + nums[i];
            ans = max(ans, dp[i]);
            while (!q.empty() && dp[q.back()] <= dp[i]) q.pop_back();
            q.push_back(i);
        }
        return ans;
    }
};