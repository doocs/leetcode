class Solution {
public:
    bool isPalindromic(string s) {
        string t;
        for (unsigned char c : s) {
            for (int i = 7; i >= 0; --i) {
                t += char('0' + ((c >> i) & 1));
            }
        }
        return ranges::equal(t, t | views::reverse);
    }
};
