class Solution {
public:
    bool checkValidString(string s) {
        int n = s.size();
        int left = 0, asterisk = 0;
        for (int i = 0; i < n; ++i) {
            if (s[i] == '(') {
                ++left;
            } else if (s[i] == ')') {
                if (left > 0)
                    --left;
                else if (asterisk > 0)
                    --asterisk;
                else
                    return false;
            } else {
                ++asterisk;
            }
        }
        int right = 0;
        asterisk = 0;
        for (int i = n - 1; i >= 0; --i) {
            if (s[i] == ')') {
                ++right;
            } else if (s[i] == '(') {
                if (right > 0)
                    --right;
                else if (asterisk > 0)
                    --asterisk;
                else
                    return false;
            } else {
                ++asterisk;
            }
        }
        return true;
    }
};
