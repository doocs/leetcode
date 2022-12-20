class Solution {
public:
    int longestPalindrome(string word1, string word2) {
        string s = word1 + word2;
        int n = s.size();
        int f[n][n];
        memset(f, 0, sizeof f);
        for (int i = 0; i < n; ++i) f[i][i] = 1;
        int ans = 0;
        for (int i = n - 2; ~i; --i) {
            for (int j = i + 1; j < n; ++j) {
                if (s[i] == s[j]) {
                    f[i][j] = f[i + 1][j - 1] + 2;
                    if (i < word1.size() && j >= word1.size()) {
                        ans = max(ans, f[i][j]);
                    }
                } else {
                    f[i][j] = max(f[i + 1][j], f[i][j - 1]);
                }
            }
        }
        return ans;
    }
};