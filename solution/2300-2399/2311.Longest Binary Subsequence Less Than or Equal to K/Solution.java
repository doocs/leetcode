class Solution {
    public int longestSubsequence(String s, int k) {
        int ans = 0;
        long v = 0;
        for (int i = s.length() - 1; i >= 0; --i) {
            if (s.charAt(i) == '0') {
                ++ans;
            } else {
                if (ans < 32 && v + (1L << ans) <= k) {
                    v += 1L << ans;
                    ++ans;
                }
            }
        }
        return ans;
    }
}