class Solution {
public:
    char repeatedCharacter(string s) {
        int mask = 0;
        for (int i = 0;; ++i) {
            if (mask >> (s[i] - 'a') & 1) {
                return s[i];
            }
            mask |= 1 << (s[i] - 'a');
        }
    }
};