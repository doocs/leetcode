class Solution {
public:
    int numDupDigitsAtMostN(int n) {
        return n - f(n);
    }

private:
    int nums[11];
    int dp[11][1 << 11];

    int f(int n) {
        memset(dp, -1, sizeof(dp));
        int i = -1;
        for (; n; n /= 10) {
            nums[++i] = n % 10;
        }
        return dfs(i, 0, true, true);
    }

    int dfs(int pos, int mask, bool lead, bool limit) {
        if (pos < 0) {
            return lead ? 0 : 1;
        }
        if (!lead && !limit && dp[pos][mask] != -1) {
            return dp[pos][mask];
        }
        int up = limit ? nums[pos] : 9;
        int ans = 0;
        for (int i = 0; i <= up; ++i) {
            if (mask >> i & 1) {
                continue;
            }
            if (i == 0 && lead) {
                ans += dfs(pos - 1, mask, lead, limit && i == up);
            } else {
                ans += dfs(pos - 1, mask | 1 << i, false, limit && i == up);
            }
        }
        if (!lead && !limit) {
            dp[pos][mask] = ans;
        }
        return ans;
    }
};