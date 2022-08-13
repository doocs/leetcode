class Solution {
public:
    int totalSteps(vector<int>& nums) {
        stack<int> stk;
        int ans = 0, n = nums.size();
        vector<int> dp(n);
        for (int i = n - 1; i >= 0; --i) {
            while (!stk.empty() && nums[i] > nums[stk.top()]) {
                dp[i] = max(dp[i] + 1, dp[stk.top()]);
                ans = max(ans, dp[i]);
                stk.pop();
            }
            stk.push(i);
        }
        return ans;
    }
};