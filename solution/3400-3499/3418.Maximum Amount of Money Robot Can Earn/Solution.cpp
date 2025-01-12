class Solution {
public:
    int maximumAmount(vector<vector<int>>& coins) {
        int m = coins.size(), n = coins[0].size();
        vector<vector<vector<int>>> f(m, vector<vector<int>>(n, vector<int>(3, -1)));
        auto dfs = [&](this auto&& dfs, int i, int j, int k) -> int {
            if (i >= m || j >= n) {
                return INT_MIN / 2;
            }
            if (f[i][j][k] != -1) {
                return f[i][j][k];
            }
            if (i == m - 1 && j == n - 1) {
                return k > 0 ? max(0, coins[i][j]) : coins[i][j];
            }
            int ans = coins[i][j] + max(dfs(i + 1, j, k), dfs(i, j + 1, k));
            if (coins[i][j] < 0 && k > 0) {
                ans = max({ans, dfs(i + 1, j, k - 1), dfs(i, j + 1, k - 1)});
            }
            return f[i][j][k] = ans;
        };
        return dfs(0, 0, 2);
    }
};
