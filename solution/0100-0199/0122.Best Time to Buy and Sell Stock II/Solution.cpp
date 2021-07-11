class Solution {
public:
    int maxProfit(vector<int>& prices) {
        int res = 0;
        for (int i = 1; i < prices.size(); ++i) {
            int t = prices[i] - prices[i - 1];
            res += max(t, 0);
        }
        return res;
    }
};