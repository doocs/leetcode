class Solution {
public:
    int findIntegers(int n) {
        int m = 32 - __builtin_clz(n);
        int f[m][2];
        memset(f, -1, sizeof(f));
        auto dfs = [&](auto&& dfs, int i, int pre, bool limit) -> int {
            if (i < 0) {
                return 1;
            }
            if (!limit && f[i][pre] != -1) {
                return f[i][pre];
            }
            int up = limit ? (n >> i & 1) : 1;
            int ans = 0;
            for (int j = 0; j <= up; ++j) {
                if (j && pre) {
                    continue;
                }
                ans += dfs(dfs, i - 1, j, limit && j == up);
            }
            if (!limit) {
                f[i][pre] = ans;
            }
            return ans;
        };
        return dfs(dfs, m - 1, 0, true);
    }
};
