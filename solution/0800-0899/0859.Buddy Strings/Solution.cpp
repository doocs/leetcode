class Solution {
public:
    bool buddyStrings(string s, string goal) {
        int m = s.size(), n = goal.size();
        if (m != n) return false;
        int diff = 0;
        vector<int> cnt1(26);
        vector<int> cnt2(26);
        for (int i = 0; i < n; ++i) {
            ++cnt1[s[i] - 'a'];
            ++cnt2[goal[i] - 'a'];
            if (s[i] != goal[i]) ++diff;
        }
        bool f = false;
        for (int i = 0; i < 26; ++i) {
            if (cnt1[i] != cnt2[i]) return false;
            if (cnt1[i] > 1) f = true;
        }
        return diff == 2 || (diff == 0 && f);
    }
};