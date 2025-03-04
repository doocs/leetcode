public class Solution {
    private string s;
    private int n;

    public String LongestPalindrome(string s) {
        this.s = s;
        n = s.Length;
        int start = 0, mx = 1;
        for (int i = 0; i < n; ++i) {
            int a = F(i, i);
            int b = F(i, i + 1);
            int t = Math.Max(a, b);
            if (mx < t) {
                mx = t;
                start = i - ((t - 1) >> 1);
            }
        }
        return s.Substring(start, start + mx);
    }

    private int F(int l, int r) {
        while (l >= 0 && r < n && s[l] == s[r]) {
            --l;
            ++r;
        }
        return r - l - 1;
    }
}