class Solution {
public:
    vector<int> finalPrices(vector<int>& prices) {
        stack<int> stk;
        int n = prices.size();
        vector<int> ans(n);
        for (int i = n - 1; i >= 0; --i) {
            ans[i] = prices[i];
            while (!stk.empty() && prices[stk.top()] > prices[i]) {
                stk.pop();
            }
            if (!stk.empty()) {
                ans[i] -= prices[stk.top()];
            }
            stk.push(i);
        }
        return ans;
    }
};