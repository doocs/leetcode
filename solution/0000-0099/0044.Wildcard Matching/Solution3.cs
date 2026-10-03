public class Solution {
    public bool IsMatch(string s, string p) {
        int m = s.Length, n = p.Length;
        bool[,] f = new bool[m + 1, n + 1];
        f[0, 0] = true;
        for (int j = 1; j <= n; ++j) {
            if (p[j - 1] == '*') {
                f[0, j] = f[0, j - 1];
            }
        }
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (p[j - 1] == '*') {
                    f[i, j] = f[i - 1, j] || f[i, j - 1] || f[i - 1, j - 1];
                } else {
                    f[i, j] = f[i - 1, j - 1] && (p[j - 1] == '?' || s[i - 1] == p[j - 1]);
                }
            }
        }
        return f[m, n];
    }
}
