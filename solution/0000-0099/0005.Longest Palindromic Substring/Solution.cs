public class Solution {
    public string LongestPalindrome(string s) {
        int n = s.Length;
        bool[,] f = new bool[n, n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; ++j) {
                f[i, j] = true;
            }
        }
        int k = 0, mx = 1;
        for (int i = n - 2; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) {
                f[i, j] = false;
                if (s[i] == s[j]) {
                    f[i, j] = f[i + 1, j - 1];
                    if (f[i, j] && mx < j - i + 1) {
                        mx = j - i + 1;
                        k = i;
                    }
                }
            }
        }
        return s.Substring(k, mx);
    }
}
