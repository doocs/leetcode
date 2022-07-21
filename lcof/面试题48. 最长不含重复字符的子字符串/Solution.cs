public class Solution {
    public int LengthOfLongestSubstring(string s) {
        var set = new HashSet<char>();
        int ans = 0;
        for (int l=0, r=0; r < s.Length; r++) {
            while (set.Contains(s[r]))
            {
                set.Remove(s[l++]);
            }
            ans = Math.Max(r - l + 1, ans);
            set.Add(s[r]);
        }
        return ans;
    }
}