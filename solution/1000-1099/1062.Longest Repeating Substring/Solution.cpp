class Solution {
public:
    int longestRepeatingSubstring(string s) {
        int n = s.length();
        int f[n][n];
        memset(f, 0, sizeof(f));
        int ans = 0;
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                if (s[i] == s[j]) {
                    f[i][j] = 1 + (j > 0 ? f[i - 1][j - 1] : 0);
                    ans = max(ans, f[i][j]);
                }
            }
        }
        return ans;
    }
};
