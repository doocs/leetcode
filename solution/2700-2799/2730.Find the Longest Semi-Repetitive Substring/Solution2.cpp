class Solution {
public:
    int longestSemiRepetitiveSubstring(string s) {
        int n = s.length();
        int cnt = 0, l = 0;
        for (int i = 1; i < n; ++i) {
            cnt += s[i] == s[i - 1] ? 1 : 0;
            if (cnt > 1) {
                cnt -= s[l] == s[++l] ? 1 : 0;
            }
        }
        return n - l;
    }
};