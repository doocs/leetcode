using ll = long long;

class Solution {
public:
    int numberOfStableArrays(int zero, int one, int limit) {
        this->limit = limit;
        f = vector<vector<array<ll, 2>>>(zero + 1, vector<array<ll, 2>>(one + 1, {-1, -1}));
        return (dfs(zero, one, 0) + dfs(zero, one, 1)) % mod;
    }

private:
    const int mod = 1e9 + 7;
    int limit;
    vector<vector<array<ll, 2>>> f;

    ll dfs(int i, int j, int k) {
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
            res = (dfs(i - 1, j, 0) + dfs(i - 1, j, 1) - dfs(i - limit - 1, j, 1) + mod) % mod;
        } else {
            res = (dfs(i, j - 1, 0) + dfs(i, j - 1, 1) - dfs(i, j - limit - 1, 0) + mod) % mod;
        }
        return res;
    }
};