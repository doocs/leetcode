public class Solution {
    public bool IsInterleave(string s1, string s2, string s3) {
        int m = s1.Length, n = s2.Length;
        if (m + n != s3.Length) {
            return false;
        }
        bool[,] f = new bool[m + 1, n + 1];
        f[0, 0] = true;
        for (int i = 0; i <= m; ++i) {
            for (int j = 0; j <= n; ++j) {
                int k = i + j - 1;
                if (i > 0 && s1[i - 1] == s3[k]) {
                    f[i, j] = f[i - 1, j];
                }
                if (j > 0 && s2[j - 1] == s3[k]) {
                    f[i, j] |= f[i, j - 1];
                }
            }
        }
        return f[m, n];
    }
}
