class Solution {
public:
    int deleteString(string s) {
        int n = s.size();
        int g[n + 1][n + 1];
        memset(g, 0, sizeof(g));
        for (int i = n - 1; ~i; --i) {
            for (int j = i + 1; j < n; ++j) {
                if (s[i] == s[j]) {
                    g[i][j] = g[i + 1][j + 1] + 1;
                }
            }
        }
        int f[n];
        memset(f, 0, sizeof(f));
        function<int(int)> dfs = [&](int i) -> int {
            if (i == n) {
                return 0;
            }
            if (f[i]) {
                return f[i];
            }
            f[i] = 1;
            for (int j = 1; j <= (n - i) / 2; ++j) {
                if (g[i][i + j] >= j) {
                    f[i] = max(f[i], 1 + dfs(i + j));
                }
            }
            return f[i];
        };
        return dfs(0);
    }
};