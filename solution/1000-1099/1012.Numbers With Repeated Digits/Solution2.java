class Solution {
    private int[] nums = new int[11];
    private Integer[][] dp = new Integer[11][1 << 11];

    public int numDupDigitsAtMostN(int n) {
        return n - f(n);
    }

    private int f(int n) {
        int i = -1;
        for (; n > 0; n /= 10) {
            nums[++i] = n % 10;
        }
        return dfs(i, 0, true, true);
    }

    private int dfs(int pos, int mask, boolean lead, boolean limit) {
        if (pos < 0) {
            return lead ? 0 : 1;
        }
        if (!lead && !limit && dp[pos][mask] != null) {
            return dp[pos][mask];
        }
        int ans = 0;
        int up = limit ? nums[pos] : 9;
        for (int i = 0; i <= up; ++i) {
            if ((mask >> i & 1) == 1) {
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
}