public class Solution {
    public int LengthOfLongestSubstring(string s) {
        var vis = new HashSet<char>();
        int ans = 0;
        for (int i = 0, j = 0; i < s.Length; ++i) {
            while (vis.Contains(s[i])) {
                vis.Remove(s[j++]);
            }
            vis.Add(s[i]);
            ans = Math.Max(ans, i - j + 1);
        }
        return ans;
    }
}
