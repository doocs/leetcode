class Solution {
    private int[] a = new int[11];
    private int[][] dp = new int[11][1 << 11];

    public int countSpecialNumbers(int n) {
        return f(n);
    }

    private int f(int n) {
        for (var e : dp) {
            Arrays.fill(e, -1);
        }
        int len = 0;
        while (n > 0) {
            a[++len] = n % 10;
            n /= 10;
        }
        return dfs(len, 0, true, true);
    }

    private int dfs(int pos, int mask, boolean lead, boolean limit) {
        if (pos <= 0) {
            return lead ? 0 : 1;
        }
        if (!lead && !limit && dp[pos][mask] != -1) {
            return dp[pos][mask];
        }
        int up = limit ? a[pos] : 9;
        int ans = 0;
        for (int i = 0; i <= up; ++i) {
            if (((mask >> i) & 1) == 1) {
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