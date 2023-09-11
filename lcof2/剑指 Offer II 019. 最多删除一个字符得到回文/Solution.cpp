class Solution {
public:
    bool validPalindrome(string s) {
        auto check = [&](int i, int j) {
            for (; i < j; ++i, --j) {
                if (s[i] != s[j]) {
                    return false;
                }
            }
            return true;
        };
        for (int i = 0, j = s.size() - 1; i < j; ++i, --j) {
            if (s[i] != s[j]) {
                return check(i + 1, j) || check(i, j - 1);
            }
        }
        return true;
    }
};