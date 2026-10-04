class Solution {
public:
    int deleteString(string s) {
        int n = s.size();
        vector<vector<int>> g(n + 1, vector<int>(n + 1));
        for (int i = n - 1; ~i; --i) {
            for (int j = i + 1; j < n; ++j) {
                if (s[i] == s[j]) {
                    g[i][j] = g[i + 1][j + 1] + 1;
                }
            }
        }
        int f[n];
        for (int i = n - 1; ~i; --i) {
            f[i] = 1;
            for (int j = 1; j <= (n - i) / 2; ++j) {
                if (g[i][i + j] >= j) {
                    f[i] = max(f[i], f[i + j] + 1);
                }
            }
        }
        return f[0];
    }
};