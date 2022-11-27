class Solution {
public:
    int appendCharacters(string s, string t) {
        int m = s.size(), n = t.size();
        for (int i = 0, j = 0; j < n; ++j) {
            while (i < m && s[i] != t[j]) {
                ++i;
            }
            if (i++ == m) {
                return n - j;
            }
        }
        return 0;
    }
};