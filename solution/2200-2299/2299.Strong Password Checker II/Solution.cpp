class Solution {
public:
    bool strongPasswordCheckerII(string password) {
        if (password.size() < 8) return false;
        int ans = 0;
        char prev = '.';
        for (char& c : password) {
            if (c == prev) return false;
            prev = c;
            if (c >= 'a' && c <= 'z')
                ans |= 1;
            else if (c >= 'A' && c <= 'Z')
                ans |= 2;
            else if (c >= '0' && c <= '9')
                ans |= 4;
            else
                ans |= 8;
        }
        return ans == 15;
    }
};