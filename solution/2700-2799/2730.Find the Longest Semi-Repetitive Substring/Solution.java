class Solution {
    public int longestSemiRepetitiveSubstring(String s) {
        int ans = 1, n = s.length();
        for (int i = 1, j = 0, cnt = 0; i < n; ++i) {
            cnt += s.charAt(i) == s.charAt(i - 1) ? 1 : 0;
            for (; cnt > 1; ++j) {
                cnt -= s.charAt(j) == s.charAt(j + 1) ? 1 : 0;
            }
            ans = Math.max(ans, i - j + 1);
        }
        return ans;
    }
}