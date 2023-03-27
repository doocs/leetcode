class Solution {
public:
    bool canConstruct(string ransomNote, string magazine) {
        int cnt[26]{};
        for (char& c : magazine) {
            ++cnt[c - 'a'];
        }
        for (char& c : ransomNote) {
            if (--cnt[c - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }
};