class Solution {
public:
    bool canConvertString(string s, string t, int k) {
        if (s.size() != t.size()) {
            return false;
        }
        int cnt[26]{};
        for (int i = 0; i < s.size(); ++i) {
            int x = (t[i] - s[i] + 26) % 26;
            ++cnt[x];
        }
        for (int i = 1; i < 26; ++i) {
            if (i + 26 * (cnt[i] - 1) > k) {
                return false;
            }
        }
        return true;
    }
};