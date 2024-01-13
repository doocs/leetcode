class Solution {
    public int minDeletionSize(String[] strs) {
        int n = strs[0].length();
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int mx = 1;
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                if (check(i, j, strs)) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            mx = Math.max(mx, dp[i]);
        }
        return n - mx;
    }

    private boolean check(int i, int j, String[] strs) {
        for (String s : strs) {
            if (s.charAt(i) < s.charAt(j)) {
                return false;
            }
        }
        return true;
    }
}