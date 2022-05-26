class Solution {
public:
    int maxProfit(vector<int>& prices) {
        int f1 = -prices[0], f2 = 0, f3 = 0;
        for (int i = 1; i < prices.size(); ++i) {
            int pf1 = f1, pf2 = f2, pf3 = f3;
            f1 = max(pf1, pf3 - prices[i]);
            f2 = max(pf2, pf1 + prices[i]);
            f3 = max(pf3, pf2);
        }
        return f2;
    }
};