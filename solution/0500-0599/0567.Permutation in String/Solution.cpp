class Solution {
public:
    bool checkInclusion(string s1, string s2) {
        int n = s1.size(), m = s2.size();
        if (n > m) {
            return false;
        }
        vector<int> cnt(26);
        for (int i = 0; i < n; ++i) {
            --cnt[s1[i] - 'a'];
            ++cnt[s2[i] - 'a'];
        }
        int diff = 0;
        for (int x : cnt) {
            if (x) {
                ++diff;
            }
        }
        if (diff == 0) {
            return true;
        }
        for (int i = n; i < m; ++i) {
            int a = s2[i - n] - 'a';
            int b = s2[i] - 'a';
            if (cnt[b] == 0) {
                ++diff;
            }
            if (++cnt[b] == 0) {
                --diff;
            }
            if (cnt[a] == 0) {
                ++diff;
            }
            if (--cnt[a] == 0) {
                --diff;
            }
            if (diff == 0) {
                return true;
            }
        }
        return false;
    }
};