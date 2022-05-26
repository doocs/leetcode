class Solution {
public:
    int maxProfit(vector<int>& prices) {
        int res = 0, mi = prices[0];
        for (int i = 1; i < prices.size(); ++i) {
            res = max(res, prices[i] - mi);
            mi = min(mi, prices[i]);
        }
        return res;
    }
};