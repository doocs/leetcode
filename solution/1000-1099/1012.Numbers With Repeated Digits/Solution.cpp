class Solution {
public:
    int numDupDigitsAtMostN(int n) {
        string s = to_string(n);
        int m = s.size();
        int f[m][1 << 10];
        memset(f, -1, sizeof(f));
        auto dfs = [&](this auto&& dfs, int i, int mask, bool lead, bool limit) -> int {
            if (i >= m) {
                return lead ^ 1;
            }
            if (!lead && !limit && f[i][mask] != -1) {
                return f[i][mask];
            }
            int up = limit ? s[i] - '0' : 9;
            int ans = 0;
            for (int j = 0; j <= up; ++j) {
                if (lead && j == 0) {
                    ans += dfs(i + 1, mask, true, limit && j == up);
                } else if (mask >> j & 1 ^ 1) {
                    ans += dfs(i + 1, mask | (1 << j), false, limit && j == up);
                }
            }
            if (!lead && !limit) {
                f[i][mask] = ans;
            }
            return ans;
        };
        return n - dfs(0, 0, true, true);
    }
};
