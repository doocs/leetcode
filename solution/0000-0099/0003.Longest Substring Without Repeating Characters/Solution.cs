public class Solution {
    public int LengthOfLongestSubstring(string s) {
        int n = s.Length;
        int ans = 0;
        var cnt = new int[128];
        for (int l = 0, r = 0; r < n; ++r) {
            ++cnt[s[r]];
            while (cnt[s[r]] > 1) {
                --cnt[s[l++]];
            }
            ans = Math.Max(ans, r - l + 1);
        }
        return ans;
    }
}
