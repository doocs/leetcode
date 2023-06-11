class Solution {
    public int longestSemiRepetitiveSubstring(String s) {
        int n = s.length();
        int ans = 0;
        for (int i = 0, j = 0, cnt = 0; i < n; ++i) {
            if (i > 0 && s.charAt(i) == s.charAt(i - 1)) {
                ++cnt;
            }
            while (cnt > 1) {
                if (s.charAt(j) == s.charAt(j + 1)) {
                    --cnt;
                }
                ++j;
            }
            ans = Math.max(ans, i - j + 1);
        }
        return ans;
    }
}