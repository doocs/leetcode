class Solution {
public:
    int a[33];
    int dp[33][2];

    int findIntegers(int n) {
        int len = 0;
        while (n) {
            a[++len] = n & 1;
            n >>= 1;
        }
        memset(dp, -1, sizeof dp);
        return dfs(len, 0, true);
    }

    int dfs(int pos, int pre, bool limit) {
        if (pos <= 0) {
            return 1;
        }
        if (!limit && dp[pos][pre] != -1) {
            return dp[pos][pre];
        }
        int ans = 0;
        int up = limit ? a[pos] : 1;
        for (int i = 0; i <= up; ++i) {
            if (!(pre == 1 && i == 1)) {
                ans += dfs(pos - 1, i, limit && i == up);
            }
        }
        if (!limit) {
            dp[pos][pre] = ans;
        }
        return ans;
    }
};