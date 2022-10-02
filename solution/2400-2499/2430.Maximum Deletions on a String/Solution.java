class Solution {
    public int deleteString(String s) {
        int n = s.length();
        int[][] lcp = new int[n + 1][n + 1];
        for (int i = n - 1; i >= 0; --i) {
            for (int j = n - 1; j >= 0; --j) {
                if (s.charAt(i) == s.charAt(j)) {
                    lcp[i][j] = 1 + lcp[i + 1][j + 1];
                }
            }
        }
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = n - 1; i >= 0; --i) {
            for (int j = 1; j <= (n - i) / 2; ++j) {
                if (lcp[i][i + j] >= j) {
                    dp[i] = Math.max(dp[i], dp[i + j] + 1);
                }
            }
        }
        return dp[0];
    }
}