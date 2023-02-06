class Solution {
public:
    char firstUniqChar(string s) {
        int cnt[26]{};
        for (char& c : s) {
            ++cnt[c - 'a'];
        }
        for (char& c : s) {
            if (cnt[c - 'a'] == 1) {
                return c;
            }
        }
        return ' ';
    }
};