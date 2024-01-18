class Solution {
public:
    int minimumCoins(vector<int>& prices) {
        int n = prices.size();
        for (int i = (n - 1) / 2; i; --i) {
            prices[i - 1] += *min_element(prices.begin() + i, prices.begin() + 2 * i + 1);
        }
        return prices[0];
    }
};