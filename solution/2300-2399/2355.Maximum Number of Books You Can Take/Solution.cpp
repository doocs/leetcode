using ll = long long;

class Solution {
public:
    long long maximumBooks(vector<int>& books) {
        int n = books.size();
        vector<int> nums(n);
        for (int i = 0; i < n; ++i) nums[i] = books[i] - i;
        vector<int> left(n, -1);
        stack<int> stk;
        for (int i = 0; i < n; ++i) {
            while (!stk.empty() && nums[stk.top()] >= nums[i]) stk.pop();
            if (!stk.empty()) left[i] = stk.top();
            stk.push(i);
        }
        vector<ll> dp(n);
        dp[0] = books[0];
        ll ans = 0;
        for (int i = 0; i < n; ++i) {
            int v = books[i];
            int j = left[i];
            int cnt = min(v, i - j);
            int u = v - cnt + 1;
            ll s = 1ll * (u + v) * cnt / 2;
            dp[i] = s + (j == -1 ? 0 : dp[j]);
            ans = max(ans, dp[i]);
        }
        return ans;
    }
};