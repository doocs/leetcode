class Solution {
public:
    bool scoreBalance(string s) {
        int l = 0, r = 0;
        for (char c : s) {
            int x = c - 'a' + 1;
            r += x;
        }
        for (int i = 0; i < s.size() - 1; ++i) {
            int x = s[i] - 'a' + 1;
            l += x;
            r -= x;
            if (l == r) {
                return true;
            }
        }
        return false;
    }
};
