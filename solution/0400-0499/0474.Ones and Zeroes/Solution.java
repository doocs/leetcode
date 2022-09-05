class Solution {
    public int findMaxForm(String[] strs, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        for (String s : strs) {
            int[] t = count(s);
            for (int i = m; i >= t[0]; --i) {
                for (int j = n; j >= t[1]; --j) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - t[0]][j - t[1]] + 1);
                }
            }
        }
        return dp[m][n];
    }

    private int[] count(String s) {
        int n0 = 0;
        for (char c : s.toCharArray()) {
            if (c == '0') {
                ++n0;
            }
        }
        return new int[] {n0, s.length() - n0};
    }
}