class Solution {
    private int[] a = new int[33];
    private int[][] dp = new int[33][2];

    public int findIntegers(int n) {
        int len = 0;
        while (n > 0) {
            a[++len] = n & 1;
            n >>= 1;
        }
        for (var e : dp) {
            Arrays.fill(e, -1);
        }
        return dfs(len, 0, true);
    }

    private int dfs(int pos, int pre, boolean limit) {
        if (pos <= 0) {
            return 1;
        }
        if (!limit && dp[pos][pre] != -1) {
            return dp[pos][pre];
        }
        int up = limit ? a[pos] : 1;
        int ans = 0;
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
}