public class Solution {
    public int LongestSubsequence(string s, int k) {
        int ans = 0, v = 0;
        for (int i = s.Length - 1; i >= 0; --i) {
            if (s[i] == '0') {
                ++ans;
            } else if (ans < 30 && (v | 1 << ans) <= k) {
                v |= 1 << ans;
                ++ans;
            }
        }
        return ans;
    }
}
