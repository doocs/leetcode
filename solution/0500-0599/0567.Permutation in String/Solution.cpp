class Solution {
public:
    bool checkInclusion(string s1, string s2) {
        int need = 0;
        int cnt[26]{};
        for (char c : s1) {
            if (++cnt[c - 'a'] == 1) {
                ++need;
            }
        }
        int m = s1.size(), n = s2.size();
        for (int i = 0; i < n; ++i) {
            int c = s2[i] - 'a';
            if (--cnt[c] == 0) {
                --need;
            }
            if (i >= m) {
                c = s2[i - m] - 'a';
                if (++cnt[c] == 1) {
                    ++need;
                }
            }
            if (need == 0) {
                return true;
            }
        }
        return false;
    }
};
