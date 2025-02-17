class Solution {
public:
    bool hasSpecialSubstring(string s, int k) {
        int n = s.length();
        for (int l = 0, cnt = 0; l < n;) {
            int r = l + 1;
            while (r < n && s[r] == s[l]) {
                ++r;
            }
            if (r - l == k) {
                return true;
            }
            l = r;
        }
        return false;
    }
};
