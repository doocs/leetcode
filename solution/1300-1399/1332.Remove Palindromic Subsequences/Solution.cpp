class Solution {
public:
    int removePalindromeSub(string s) {
        if (s.empty())
            return 0;
        string t = s;
        reverse(s.begin(), s.end());
        if (s == t)
            return 1;
        return 2;
    }
};