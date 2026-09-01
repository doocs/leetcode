class Solution {
public:
    long long maximumScore(vector<vector<int>>& grid) {
        int n = grid.size();
        const long long inf = LLONG_MIN / 2;
        vector<vector<long long>> s(n, vector<long long>(n + 1));
        for (int j = 0; j < n; ++j) {
            for (int i = 0; i < n; ++i) {
                s[j][i + 1] = s[j][i] + grid[i][j];
            }
        }
        vector<vector<long long>> f(n + 1, vector<long long>(n + 1, inf));
        for (int h = 0; h <= n; ++h) {
            f[h][0] = 0;
        }
        for (int j = 0; j < n - 1; ++j) {
            vector<vector<long long>> g(n + 1, vector<long long>(n + 1, inf));
            for (int h1 = 0; h1 <= n; ++h1) {
                vector<long long> pre(n + 2, inf), suf(n + 2, inf);
                pre[0] = f[h1][0];
                for (int h2 = 1; h2 <= n; ++h2) {
                    pre[h2] = max(pre[h2 - 1], f[h1][h2]);
                }
                for (int h2 = n; h2 >= 0; --h2) {
                    long long v = f[h1][h2] == inf ? inf : f[h1][h2] + max(0LL, s[j][h2] - s[j][h1]);
                    suf[h2] = max(suf[h2 + 1], v);
                }
                for (int hp = 0; hp <= n; ++hp) {
                    long long add = max(0LL, s[j][hp] - s[j][h1]);
                    long long v1 = pre[hp] == inf ? inf : pre[hp] + add;
                    g[hp][h1] = max(v1, suf[hp + 1]);
                }
            }
            f.swap(g);
        }
        long long ans = 0;
        for (int h1 = 0; h1 <= n; ++h1) {
            for (int h2 = 0; h2 <= n; ++h2) {
                if (f[h1][h2] != inf) {
                    ans = max(ans, f[h1][h2] + max(0LL, s[n - 1][h2] - s[n - 1][h1]));
                }
            }
        }
        return ans;
    }
};
