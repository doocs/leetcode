class Solution {
public:
    bool checkInclusion(string s1, string s2) {
        int m = s1.size(), n = s2.size();
        if (m > n) {
            return false;
        }
        vector<int> cnt1(26), cnt2(26);
        for (int i = 0; i < m; ++i) {
            ++cnt1[s1[i] - 'a'];
            ++cnt2[s2[i] - 'a'];
        }
        if (cnt1 == cnt2) {
            return true;
        }
        for (int i = m; i < n; ++i) {
            ++cnt2[s2[i] - 'a'];
            --cnt2[s2[i - m] - 'a'];
            if (cnt1 == cnt2) {
                return true;
            }
        }
        return false;
    }
};