class Solution {
public:
    int maxProfit(int k, vector<int>& prices) {
        int n = prices.size();
        int f[k + 1][2];
        memset(f, 0, sizeof(f));
        for (int j = 1; j <= k; ++j) {
            f[j][1] = -prices[0];
        }
        for (int i = 1; i < n; ++i) {
            for (int j = k; j; --j) {
                f[j][0] = max(f[j][1] + prices[i], f[j][0]);
                f[j][1] = max(f[j - 1][0] - prices[i], f[j][1]);
            }
        }
        return f[k][0];
    }
};