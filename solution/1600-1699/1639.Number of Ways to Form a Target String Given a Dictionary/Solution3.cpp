class Solution {
public:
    int numWays(vector<string>& words, string target) {
        const int mod = 1e9 + 7;
        int m = target.size(), n = words[0].size();
        vector<vector<int>> cnt(n, vector<int>(26));
        for (auto& w : words) {
            for (int j = 0; j < n; ++j) {
                ++cnt[j][w[j] - 'a'];
            }
        }
        vector<vector<int>> f(m + 1, vector<int>(n + 1));
        for (int j = 0; j <= n; ++j) {
            f[m][j] = 1;
        }
        for (int i = m - 1; i >= 0; --i) {
            for (int j = n - 1; j >= 0; --j) {
                long long ans = f[i][j + 1];
                ans += 1LL * f[i + 1][j + 1] * cnt[j][target[i] - 'a'];
                f[i][j] = ans % mod;
            }
        }
        return f[0][0];
    }
};
