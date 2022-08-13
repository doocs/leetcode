class Solution {
public:
    int countSubstrings(string s, string t) {
        int m = s.size(), n = t.size();
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (s[i] == t[j]) continue;
                int l = 1, r = 1;
                while (i - l >= 0 && j - l >= 0 && s[i - l] == t[j - l]) ++l;
                while (i + r < m && j + r < n && s[i + r] == t[j + r]) ++r;
                ans += l * r;
            }
        }
        return ans;
    }
};