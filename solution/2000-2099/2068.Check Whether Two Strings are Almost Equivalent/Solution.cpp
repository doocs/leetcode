class Solution {
public:
    bool checkAlmostEquivalent(string word1, string word2) {
        int cnt[26]{};
        for (char& c : word1) {
            ++cnt[c - 'a'];
        }
        for (char& c : word2) {
            --cnt[c - 'a'];
        }
        for (int i = 0; i < 26; ++i) {
            if (abs(cnt[i]) > 3) {
                return false;
            }
        }
        return true;
    }
};