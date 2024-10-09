class Solution {
public:
    int numberOfStableArrays(int zero, int one, int limit) {
        const int mod = 1e9 + 7;
        using ll = long long;
        vector<vector<array<ll, 2>>> f = vector<vector<array<ll, 2>>>(zero + 1, vector<array<ll, 2>>(one + 1, {-1, -1}));
        auto dfs = [&](auto&& dfs, int i, int j, int k) -> ll {
            if (i < 0 || j < 0) {
                return 0;
            }
            if (i == 0) {
                return k == 1 && j <= limit;
            }
            if (j == 0) {
                return k == 0 && i <= limit;
            }
            ll& res = f[i][j][k];
            if (res != -1) {
                return res;
            }
            if (k == 0) {
                res = (dfs(dfs, i - 1, j, 0) + dfs(dfs, i - 1, j, 1) - dfs(dfs, i - limit - 1, j, 1) + mod) % mod;
            } else {
                res = (dfs(dfs, i, j - 1, 0) + dfs(dfs, i, j - 1, 1) - dfs(dfs, i, j - limit - 1, 0) + mod) % mod;
            }
            return res;
        };
        return (dfs(dfs, zero, one, 0) + dfs(dfs, zero, one, 1)) % mod;
    }
};
