class Solution {
public:
    int maxProfit(vector<int>& prices, int fee) {
        int f1 = -prices[0], f2 = 0;
        for (int i = 1; i < prices.size(); ++i) {
            f1 = max(f1, f2 - prices[i]);
            f2 = max(f2, f1 + prices[i] - fee);
        }
        return f2;
    }
};