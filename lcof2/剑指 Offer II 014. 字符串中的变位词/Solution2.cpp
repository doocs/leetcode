class Solution {
public:
    bool checkInclusion(string s1, string s2) {
        int m = s1.size(), n = s2.size();
        if (m > n) {
            return false;
        }
        vector<int> cnt(26);
        for (int i = 0; i < m; ++i) {
            --cnt[s1[i] - 'a'];
            ++cnt[s2[i] - 'a'];
        }
        int diff = 0;
        for (int x : cnt) {
            if (x != 0) {
                ++diff;
            }
        }
        if (diff == 0) {
            return true;
        }
        for (int i = m; i < n; ++i) {
            int a = s2[i - m] - 'a';
            int b = s2[i] - 'a';
            if (cnt[a] == 0) {
                ++diff;
            }
            --cnt[a];
            if (cnt[a] == 0) {
                --diff;
            }
            if (cnt[b] == 0) {
                ++diff;
            }
            ++cnt[b];
            if (cnt[b] == 0) {
                --diff;
            }
            if (diff == 0) {
                return true;
            }
        }
        return false;
    }
};