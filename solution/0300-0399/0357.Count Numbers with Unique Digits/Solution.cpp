class Solution {
public:
    int countNumbersWithUniqueDigits(int n) {
        int f[n + 1][1 << 10];
        memset(f, -1, sizeof(f));
        auto dfs = [&](auto&& dfs, int i, int mask, bool lead) -> int {
            if (i < 0) {
                return 1;
            }
            if (!lead && f[i][mask] != -1) {
                return f[i][mask];
            }
            int ans = 0;
            for (int j = 0; j <= 9; ++j) {
                if (mask >> j & 1) {
                    continue;
                }
                if (lead && j == 0) {
                    ans += dfs(dfs, i - 1, mask, true);
                } else {
                    ans += dfs(dfs, i - 1, mask | 1 << i, false);
                }
            }
            if (!lead) {
                f[i][mask] = ans;
            }
            return ans;
        };
        return dfs(dfs, n - 1, 0, true);
    }
};
