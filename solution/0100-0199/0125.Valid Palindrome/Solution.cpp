class Solution {
public:
    bool isPalindrome(string s) {
        int i = 0, j = s.size() - 1;
        while (i < j) {
            if (!isAlphaNum(s[i]))
                ++i;
            else if (!isAlphaNum(s[j]))
                --j;
            else if ((s[i] + 32 - 'a') % 32 != (s[j] + 32 - 'a') % 32)
                return false;
            else {
                ++i;
                --j;
            }
        }
        return true;
    }

private:
    bool isAlphaNum(char& ch) {
        if (ch >= 'a' && ch <= 'z') return true;
        if (ch >= 'A' && ch <= 'Z') return true;
        if (ch >= '0' && ch <= '9') return true;
        return false;
    }
};