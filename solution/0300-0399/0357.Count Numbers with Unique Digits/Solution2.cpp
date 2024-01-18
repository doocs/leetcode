class Solution {
public:
    int dp[10][1 << 11];

    int countNumbersWithUniqueDigits(int n) {
        memset(dp, -1, sizeof dp);
        return dfs(n, 0, true);
    }

    int dfs(int pos, int mask, bool lead) {
        if (pos <= 0) {
            return 1;
        }
        if (!lead && dp[pos][mask] != -1) {
            return dp[pos][mask];
        }
        int ans = 0;
        for (int i = 0; i < 10; ++i) {
            if ((mask >> i) & 1) continue;
            if (i == 0 && lead) {
                ans += dfs(pos - 1, mask, lead);
            } else {
                ans += dfs(pos - 1, mask | 1 << i, false);
            }
        }
        if (!lead) {
            dp[pos][mask] = ans;
        }
        return ans;
    }
};