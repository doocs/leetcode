class Solution {
public:
    string getSmallestString(string s) {
        int n = s.length();
        for (int i = 1; i < n; ++i) {
            char a = s[i - 1], b = s[i];
            if (a > b && a % 2 == b % 2) {
                s[i - 1] = b;
                s[i] = a;
                break;
            }
        }
        return s;
    }
};