public class Solution {
    public bool IsInterleave(string s1, string s2, string s3) {
        int m = s1.Length, n = s2.Length;
        if (m + n != s3.Length) {
            return false;
        }
        bool[] f = new bool[n + 1];
        f[0] = true;
        for (int i = 0; i <= m; ++i) {
            for (int j = 0; j <= n; ++j) {
                int k = i + j - 1;
                if (i > 0) {
                    f[j] &= s1[i - 1] == s3[k];
                }
                if (j > 0) {
                    f[j] |= (f[j - 1] & s2[j - 1] == s3[k]);
                }
            }
        }
        return f[n];
    }
}
