class Solution {
public:
    int stoneGameV(vector<int>& stoneValue) {
        int n = stoneValue.size();
        int s[n + 1];
        s[0] = 0;
        for (int i = 1; i <= n; ++i) {
            s[i] = s[i - 1] + stoneValue[i - 1];
        }
        int f[n][n];
        memset(f, 0, sizeof(f));
        function<int(int, int)> dfs = [&](int i, int j) -> int {
            if (i == j) {
                return 0;
            }
            if (f[i][j]) {
                return f[i][j];
            }
            int ans = 0;
            int a = 0;
            for (int k = i; k < j; ++k) {
                a += stoneValue[k];
                int b = s[j + 1] - s[i] - a;
                if (a < b) {
                    if (ans >= a * 2) {
                        continue;
                    }
                    ans = max(ans, a + dfs(i, k));
                } else if (a > b) {
                    if (ans >= b * 2) {
                        break;
                    }
                    ans = max(ans, b + dfs(k + 1, j));
                } else {
                    ans = max({ans, a + dfs(i, k), b + dfs(k + 1, j)});
                }
            }
            return f[i][j] = ans;
        };
        return dfs(0, n - 1);
    }
};