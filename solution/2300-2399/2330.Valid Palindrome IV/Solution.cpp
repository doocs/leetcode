class Solution {
public:
    bool makePalindrome(string s) {
        int t = 0;
        for (int i = 0, j = s.size() - 1; i < j; ++i, --j) t += s[i] != s[j];
        return t <= 2;
    }
};