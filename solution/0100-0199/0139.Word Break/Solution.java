class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        if (s == null || s.length() == 0) {
            return false;
        }
        Set<String> words = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;

        for (int i = 1; i <= s.length(); i++) {
            for (int j = i - 1; j > -1; j--) {
                dp[i] = dp[j] && words.contains(s.substring(j, i));
                if (dp[i]) {
                    break;
                }
            }
        }

        return dp[s.length()];
    }
}