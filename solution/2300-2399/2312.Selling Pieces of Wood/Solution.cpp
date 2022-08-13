using ll = long long;

class Solution {
public:
    long long sellingWood(int m, int n, vector<vector<int>>& prices) {
        vector<vector<ll>> memo(m + 1, vector<ll>(n + 1, -1));
        vector<vector<int>> d(m + 1, vector<int>(n + 1));
        for (auto& p : prices) d[p[0]][p[1]] = p[2];
        return dfs(m, n, d, memo);
    }

    ll dfs(int m, int n, vector<vector<int>>& d, vector<vector<ll>>& memo) {
        if (memo[m][n] != -1) return memo[m][n];
        ll ans = d[m][n];
        for (int i = 1; i < m / 2 + 1; ++i) ans = max(ans, dfs(i, n, d, memo) + dfs(m - i, n, d, memo));
        for (int i = 1; i < n / 2 + 1; ++i) ans = max(ans, dfs(m, i, d, memo) + dfs(m, n - i, d, memo));
        memo[m][n] = ans;
        return ans;
    }
};