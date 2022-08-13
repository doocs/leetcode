class Solution {
public:
    int maxProfit(vector<int>& prices) {
        if (prices.size() < 2) {
            return 0;
        }

        int curMin = prices[0];
        int maxDiff = prices[1] - prices[0];

        for (int i = 2; i < prices.size(); i++) {
            if (curMin > prices[i - 1]) {
                curMin = prices[i - 1];
            }

            int diff = prices[i] - curMin;
            if (maxDiff < diff) {
                maxDiff = diff;
            }
        }

        return maxDiff > 0 ? maxDiff : 0;
    }
};