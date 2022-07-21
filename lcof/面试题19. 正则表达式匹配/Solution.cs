public class Solution {
    public bool IsMatch(string s, string p) {
        int m = s.Length, n = p.Length;
        if (n == 0) {
            return m == 0;
        }
        bool[,] dp = new bool[m+1,n+1];
        dp[0,0] = true;
        for(int j = 1; j < n + 1; j++) {
            if (p[j-1] == '*') {
                dp[0,j] = dp[0,j-2];
            } 
        }
        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (s[i-1] == p[j-1] || p[j-1] == '.') {
                    dp[i,j] = dp[i-1,j-1];
                } else if (p[j-1] == '*') {
                    if (p[j-2] == '.' || p[j-2] == s[i-1]) {
                        dp[i,j] = dp[i,j-2] || dp[i-1,j];
                    } else {
                        dp[i,j] = dp[i,j-2];
                    }
                }
            }
        }
        return dp[m,n];
    }
}