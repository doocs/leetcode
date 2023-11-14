public class Solution {
    public int CountPalindromicSubsequence(string s) {
        int ans = 0;
        for (char c = 'a'; c <= 'z'; ++c) {
            int l = s.IndexOf(c), r = s.LastIndexOf(c);
            HashSet<char> cs = new HashSet<char>();
            for (int i = l + 1; i < r; ++i) {
                cs.Add(s[i]);
            }
            ans += cs.Count;
        }
        return ans;
    }
}
