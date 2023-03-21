class Solution {
public:
    bool makePalindrome(string s) {
        int cnt = 0;
        int i = 0, j = s.size() - 1;
        for (; i < j; ++i, --j) {
            cnt += s[i] != s[j];
        }
        return cnt <= 2;
    }
};