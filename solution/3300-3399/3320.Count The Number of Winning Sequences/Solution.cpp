class Solution {
public:
    int countWinningSequences(string s) {
        int n = s.size();
        int d[26]{};
        d['W' - 'A'] = 1;
        d['E' - 'A'] = 2;
        int f[n][n + n + 1][4];
        memset(f, -1, sizeof(f));
        auto calc = [](int x, int y) -> int {
            if (x == y) {
                return 0;
            }
            if (x < y) {
                return x == 0 && y == 2 ? 1 : -1;
            }
            return x == 2 && y == 0 ? -1 : 1;
        };
        const int mod = 1e9 + 7;
        auto dfs = [&](this auto&& dfs, int i, int j, int k) -> int {
            if (n - i <= j - n) {
                return 0;
            }
            if (i >= n) {
                return j - n < 0 ? 1 : 0;
            }
            if (f[i][j][k] != -1) {
                return f[i][j][k];
            }
            int ans = 0;
            for (int l = 0; l < 3; ++l) {
                if (l == k) {
                    continue;
                }
                ans = (ans + dfs(i + 1, j + calc(d[s[i] - 'A'], l), l)) % mod;
            }
            return f[i][j][k] = ans;
        };
        return dfs(0, n, 3);
    }
};
