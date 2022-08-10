public class Solution {
    public int LengthOfLongestSubstring(string s) {
        var ss = new HashSet<char>();
        int i = 0, ans = 0;
        for (int j = 0; j < s.Length; ++j)
        {
            while (ss.Contains(s[j]))
            {
                ss.Remove(s[i++]);
            }
            ss.Add(s[j]);
            ans = Math.Max(ans, j - i + 1);
        }
        return ans;
    }
}