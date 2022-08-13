class Solution {
public:
    char repeatedCharacter(string s) {
        vector<int> cnt(26);
        for (char c : s)
            if (++cnt[c - 'a'] == 2) return c;
        return '.';
    }
};