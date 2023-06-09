class Solution {
public:
    bool areOccurrencesEqual(string s) {
        int cnt[26]{};
        for (char& c : s) {
            ++cnt[c - 'a'];
        }
        int x = 0;
        for (int& v : cnt) {
            if (v) {
                if (!x) {
                    x = v;
                } else if (x != v) {
                    return false;
                }
            }
        }
        return true;
    }
};