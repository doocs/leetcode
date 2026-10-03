class Solution {
public:
    int maxProfit(vector<int>& prices, int fee) {
        int n = prices.size();
        vector<vector<int>> f(n + 1, vector<int>(2));
        for (int i = n - 1; i >= 0; --i) {
            for (int j = 0; j < 2; ++j) {
                int ans = f[i + 1][j];
                if (j) {
                    ans = max(ans, prices[i] + f[i + 1][0] - fee);
                } else {
                    ans = max(ans, -prices[i] + f[i + 1][1]);
                }
                f[i][j] = ans;
            }
        }
        return f[0][0];
    }
};
