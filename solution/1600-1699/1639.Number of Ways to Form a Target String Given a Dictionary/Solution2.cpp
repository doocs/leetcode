class Solution {
public:
    int numWays(vector<string>& words, string target) {
        int m = target.size(), n = words[0].size();
        const int mod = 1e9 + 7;
        long long f[m + 1][n + 1];
        memset(f, 0, sizeof(f));
        fill(f[0], f[0] + n + 1, 1);
        vector<vector<int>> cnt(n, vector<int>(26));
        for (auto& w : words) {
            for (int j = 0; j < n; ++j) {
                ++cnt[j][w[j] - 'a'];
            }
        }
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                f[i][j] = f[i][j - 1] + f[i - 1][j - 1] * cnt[j - 1][target[i - 1] - 'a'];
                f[i][j] %= mod;
            }
        }
        return f[m][n];
    }
};