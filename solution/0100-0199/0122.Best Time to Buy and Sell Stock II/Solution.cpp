class Solution {
public:
    int maxProfit(vector<int>& prices) {
        int res = 0, n;
        if ((n = prices.size()) == 0) return 0;
        for (int i = 1; i < n; ++i)
        {
            int t = prices[i] - prices[i - 1];
            res += max(0, t);
        }
        return res;
    }
};