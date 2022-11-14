class Solution {
    private boolean[][] dp;
    private int[] f;
    private String s;
    private int n;
    private int k;

    public int maxPalindromes(String s, int k) {
        n = s.length();
        f = new int[n];
        this.s = s;
        this.k = k;
        dp = new boolean[n][n];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(dp[i], true);
            f[i] = -1;
        }
        for (int i = n - 1; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) {
                dp[i][j] = s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1];
            }
        }
        return dfs(0);
    }

    private int dfs(int i) {
        if (i >= n) {
            return 0;
        }
        if (f[i] != -1) {
            return f[i];
        }
        int ans = dfs(i + 1);
        for (int j = i + k - 1; j < n; ++j) {
            if (dp[i][j]) {
                ans = Math.max(ans, 1 + dfs(j + 1));
            }
        }
        f[i] = ans;
        return ans;
    }
}