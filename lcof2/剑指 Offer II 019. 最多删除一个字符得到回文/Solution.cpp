class Solution {
public:
    bool validPalindrome(string s) {
        for (int i = 0, j = s.size() - 1; i < j; ++i, --j)
            if (s[i] != s[j])
                return check(s, i + 1, j) || check(s, i, j - 1);
        return 1;
    }

    bool check(string s, int i, int j) {
        for (; i < j; ++i, --j)
            if (s[i] != s[j])
                return 0;
        return 1;
    }
};