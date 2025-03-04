class Solution {
public:
    int longestCommonPrefix(string s, string t) {
        int n = s.length(), m = t.length();
        int i = 0, j = 0;
        bool rem = false;
        while (i < n && j < m) {
            if (s[i] != t[j]) {
                if (rem) {
                    break;
                }
                rem = true;
            } else {
                ++j;
            }
            ++i;
        }
        return j;
    }
};
