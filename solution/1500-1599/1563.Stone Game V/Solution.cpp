class Solution {
public:
    int stoneGameV(vector<int>& stoneValue) {
        int n = stoneValue.size();
        int s[n + 1];
        s[0] = 0;
        for (int i = 0; i < n; i++) {
            s[i + 1] = s[i] + stoneValue[i];
        }
        int f[n][n];
        memset(f, -1, sizeof(f));
        auto dfs = [&](this auto&& dfs, int i, int j) -> int {
            if (i >= j) {
                return 0;
            }
            if (f[i][j] != -1) {
                return f[i][j];
            }
            int ans = 0, l = 0, r = s[j + 1] - s[i];
            for (int k = i; k < j; ++k) {
                l += stoneValue[k];
                r -= stoneValue[k];
                if (l < r) {
                    if (ans > l * 2) {
                        continue;
                    }
                    ans = max(ans, l + dfs(i, k));
                } else if (l > r) {
                    if (ans > r * 2) {
                        break;
                    }
                    ans = max(ans, r + dfs(k + 1, j));
                } else {
                    ans = max({ans, l + dfs(i, k), r + dfs(k + 1, j)});
                }
            }
            return f[i][j] = ans;
        };
        return dfs(0, n - 1);
    }
};
