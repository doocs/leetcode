class Solution {
public:
    int balancedStringSplit(string s) {
        int n = 0, res = 0;
        for (char c : s) {
            if (c == 'L') ++n;
            else --n;
            if (n == 0) ++res;
        }
        return res;
    }
};