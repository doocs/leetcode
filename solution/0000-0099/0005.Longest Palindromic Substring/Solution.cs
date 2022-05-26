public class Solution{
    public string LongestPalindrome(string s) {
        int n = s.Length;
        bool[,] dp = new bool[n, n];
        int mx = 1, start = 0;
        for (int j = 0; j < n; ++j)
        {
            for (int i = 0; i <= j; ++i)
            {
                if (j - i < 2)
                {
                    dp[i, j] = s[i] == s[j];
                }
                else
                {
                    dp[i, j] = dp[i + 1, j - 1] && s[i] == s[j];
                }
                if (dp[i, j] && mx < j - i + 1)
                {
                    mx = j - i + 1;
                    start = i;
                }
            }
        }
        return s.Substring(start, mx);
    }
}