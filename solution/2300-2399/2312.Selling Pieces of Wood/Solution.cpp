class Solution {
public:
    long long sellingWood(int m, int n, vector<vector<int>>& prices) {
        using ll = long long;
        ll f[m + 1][n + 1];
        int d[m + 1][n + 1];
        memset(f, -1, sizeof(f));
        memset(d, 0, sizeof(d));
        for (auto& p : prices) {
            d[p[0]][p[1]] = p[2];
        }
        function<ll(int, int)> dfs = [&](int h, int w) -> ll {
            if (f[h][w] != -1) {
                return f[h][w];
            }
            ll ans = d[h][w];
            for (int i = 1; i < h / 2 + 1; ++i) {
                ans = max(ans, dfs(i, w) + dfs(h - i, w));
            }
            for (int i = 1; i < w / 2 + 1; ++i) {
                ans = max(ans, dfs(h, i) + dfs(h, w - i));
            }
            return f[h][w] = ans;
        };
        return dfs(m, n);
    }
};