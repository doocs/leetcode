class Solution {
public:
    char repeatedCharacter(string s) {
        int cnt[26]{};
        for (int i = 0;; ++i) {
            if (++cnt[s[i] - 'a'] == 2) {
                return s[i];
            }
        }
    }
};