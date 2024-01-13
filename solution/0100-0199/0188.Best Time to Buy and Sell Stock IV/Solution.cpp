class Solution {
public:
    int maxProfit(int k, vector<int>& prices) {
        int n = prices.size();
        int f[n][k + 1][2];
        memset(f, -1, sizeof(f));
        function<int(int, int, int)> dfs = [&](int i, int j, int k) -> int {
            if (i >= n) {
                return 0;
            }
            if (f[i][j][k] != -1) {
                return f[i][j][k];
            }
            int ans = dfs(i + 1, j, k);
            if (k) {
                ans = max(ans, prices[i] + dfs(i + 1, j, 0));
            } else if (j) {
                ans = max(ans, -prices[i] + dfs(i + 1, j - 1, 1));
            }
            return f[i][j][k] = ans;
        };
        return dfs(0, k, 0);
    }
};