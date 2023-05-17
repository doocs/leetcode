class Solution {
public:
    int numberWays(vector<vector<int>>& hats) {
        int n = hats.size();
        int m = 0;
        for (auto& h : hats) {
            m = max(m, *max_element(h.begin(), h.end()));
        }
        vector<vector<int>> g(m + 1);
        for (int i = 0; i < n; ++i) {
            for (int& v : hats[i]) {
                g[v].push_back(i);
            }
        }
        const int mod = 1e9 + 7;
        int f[m + 1][1 << n];
        memset(f, 0, sizeof(f));
        f[0][0] = 1;
        for (int i = 1; i <= m; ++i) {
            for (int j = 0; j < 1 << n; ++j) {
                f[i][j] = f[i - 1][j];
                for (int k : g[i]) {
                    if (j >> k & 1) {
                        f[i][j] = (f[i][j] + f[i - 1][j ^ (1 << k)]) % mod;
                    }
                }
            }
        }
        return f[m][(1 << n) - 1];
    }
};