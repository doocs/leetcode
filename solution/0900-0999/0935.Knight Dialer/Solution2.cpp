class Solution {
public:
    int knightDialer(int n) {
        const int mod = 1e9 + 7;
        vector<vector<int>> base = {
            {0, 0, 0, 0, 1, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 1, 0, 1, 0},
            {0, 0, 0, 0, 0, 0, 0, 1, 0, 1},
            {0, 0, 0, 0, 1, 0, 0, 0, 1, 0},
            {1, 0, 0, 1, 0, 0, 0, 0, 0, 1},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {1, 1, 0, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 1, 0, 0, 0, 1, 0, 0, 0},
            {0, 1, 0, 1, 0, 0, 0, 0, 0, 0},
            {0, 0, 1, 0, 1, 0, 0, 0, 0, 0}};
        vector<vector<int>> res = pow(base, n - 1, mod);
        return accumulate(res[0].begin(), res[0].end(), 0LL) % mod;
    }

private:
    vector<vector<int>> mul(const vector<vector<int>>& a, const vector<vector<int>>& b, int mod) {
        int m = a.size(), n = b[0].size();
        vector<vector<int>> c(m, vector<int>(n, 0));
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                for (int k = 0; k < b.size(); ++k) {
                    c[i][j] = (c[i][j] + (1LL * a[i][k] * b[k][j]) % mod) % mod;
                }
            }
        }
        return c;
    }

    vector<vector<int>> pow(vector<vector<int>>& a, int n, int mod) {
        int size = a.size();
        vector<vector<int>> res(1, vector<int>(size, 1));
        while (n > 0) {
            if (n % 2 == 1) {
                res = mul(res, a, mod);
            }
            a = mul(a, a, mod);
            n /= 2;
        }
        return res;
    }
};
