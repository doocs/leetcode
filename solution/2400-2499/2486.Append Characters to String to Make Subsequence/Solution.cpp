class Solution {
public:
    int appendCharacters(string s, string t) {
        int n = t.length(), j = 0;
        for (int i = 0; i < s.size() && j < n; ++i) {
            if (s[i] == t[j]) {
                ++j;
            }
        }
        return n - j;
    }
};