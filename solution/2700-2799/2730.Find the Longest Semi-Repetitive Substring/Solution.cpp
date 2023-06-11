class Solution {
public:
    int longestSemiRepetitiveSubstring(string s) {
        int n = s.size();
        int ans = 0;
        for (int i = 0, j = 0, cnt = 0; i < n; ++i) {
            if (i && s[i] == s[i - 1]) {
                ++cnt;
            }
            while (cnt > 1) {
                if (s[j] == s[j + 1]) {
                    --cnt;
                }
                ++j;
            }
            ans = max(ans, i - j + 1);
        }
        return ans;
    }
};