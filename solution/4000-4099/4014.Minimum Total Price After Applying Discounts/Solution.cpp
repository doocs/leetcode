class Solution {
public:
    double minPrice(vector<int>& prices, vector<int>& discounts) {
        sort(prices.begin(), prices.end());
        sort(discounts.begin(), discounts.end());

        int i = prices.size() - 1;
        int j = discounts.size() - 1;

        double ans = 0;

        while (i >= 0 && j >= 0) {
            ans += prices[i] * (100 - discounts[j]) / 100.0;
            i--;
            j--;
        }

        while (i >= 0) {
            ans += prices[i];
            i--;
        }

        return ans;
    }
};