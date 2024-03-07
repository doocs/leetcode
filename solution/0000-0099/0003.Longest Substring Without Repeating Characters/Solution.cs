public class Solution {
    public int LengthOfLongestSubstring(string s) {
        int ans = 0;
        var ss = new HashSet<char>();
        for (int i = 0, j = 0; j < s.Length; ++j) {
            while (ss.Contains(s[j])) {
                ss.Remove(s[i++]);
            }
            ss.Add(s[j]);
            ans = Math.Max(ans, j - i + 1);
        }
        return ans;
    }
}
