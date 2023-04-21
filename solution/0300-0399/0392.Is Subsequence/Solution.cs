public class Solution {
    public bool IsSubsequence(string s, string t) {
        int m = s.Length, n = t.Length;
        int i = 0, j = 0;
        for (; i < m && j < n; ++j) {
            if (s[i] == t[j]) {
                ++i;
            }
        }
        return i == m;
    }
}