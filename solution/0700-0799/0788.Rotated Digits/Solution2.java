class Solution {
    private int[] a = new int[6];
    private int[][] dp = new int[6][2];

    public int rotatedDigits(int n) {
        int len = 0;
        for (var e : dp) {
            Arrays.fill(e, -1);
        }
        while (n > 0) {
            a[++len] = n % 10;
            n /= 10;
        }
        return dfs(len, 0, true);
    }

    private int dfs(int pos, int ok, boolean limit) {
        if (pos <= 0) {
            return ok;
        }
        if (!limit && dp[pos][ok] != -1) {
            return dp[pos][ok];
        }
        int up = limit ? a[pos] : 9;
        int ans = 0;
        for (int i = 0; i <= up; ++i) {
            if (i == 0 || i == 1 || i == 8) {
                ans += dfs(pos - 1, ok, limit && i == up);
            }
            if (i == 2 || i == 5 || i == 6 || i == 9) {
                ans += dfs(pos - 1, 1, limit && i == up);
            }
        }
        if (!limit) {
            dp[pos][ok] = ans;
        }
        return ans;
    }
}