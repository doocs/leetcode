public class Solution {
    public string ShortestPalindrome(string s) {
        int baseValue = 131;
        int mul = 1;
        int mod = (int)1e9 + 7;
        int prefix = 0, suffix = 0;
        int idx = 0;
        int n = s.Length;

        for (int i = 0; i < n; ++i) {
            int t = s[i] - 'a' + 1;
            prefix = (int)(((long)prefix * baseValue + t) % mod);
            suffix = (int)((suffix + (long)t * mul) % mod);
            mul = (int)(((long)mul * baseValue) % mod);
            if (prefix == suffix) {
                idx = i + 1;
            }
        }

        if (idx == n) {
            return s;
        }

        return new string(s.Substring(idx).Reverse().ToArray()) + s;
    }
}
