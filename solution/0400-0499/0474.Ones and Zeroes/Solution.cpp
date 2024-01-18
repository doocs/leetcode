class Solution {
public:
    int findMaxForm(vector<string>& strs, int m, int n) {
        int sz = strs.size();
        int f[sz + 1][m + 1][n + 1];
        memset(f, 0, sizeof(f));
        for (int i = 1; i <= sz; ++i) {
            auto [a, b] = count(strs[i - 1]);
            for (int j = 0; j <= m; ++j) {
                for (int k = 0; k <= n; ++k) {
                    f[i][j][k] = f[i - 1][j][k];
                    if (j >= a && k >= b) {
                        f[i][j][k] = max(f[i][j][k], f[i - 1][j - a][k - b] + 1);
                    }
                }
            }
        }
        return f[sz][m][n];
    }

    pair<int, int> count(string& s) {
        int a = count_if(s.begin(), s.end(), [](char c) { return c == '0'; });
        return {a, s.size() - a};
    }
};