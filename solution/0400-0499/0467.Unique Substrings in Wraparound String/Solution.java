class Solution {
    public int findSubstringInWraproundString(String p) {
        int[] dp = new int[26];
        int k = 0;
        for (int i = 0; i < p.length(); ++i) {
            char c = p.charAt(i);
            if (i > 0 && (c - p.charAt(i - 1) + 26) % 26 == 1) {
                ++k;
            } else {
                k = 1;
            }
            dp[c - 'a'] = Math.max(dp[c - 'a'], k);
        }
        int ans = 0;
        for (int v : dp) {
            ans += v;
        }
        return ans;
    }
}