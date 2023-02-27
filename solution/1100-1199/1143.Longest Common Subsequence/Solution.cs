public class Solution {
    public int LongestCommonSubsequence(string text1, string text2) {
        int m = text1.Length, n = text2.Length;
        int[,] f = new int[m + 1, n + 1];
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (text1[i - 1] == text2[j - 1]) {
                    f[i, j] = f[i - 1, j - 1] + 1;
                } else {
                    f[i, j] = Math.Max(f[i - 1, j], f[i, j - 1]);
                }
            }
        }
        return f[m, n];
    }
}