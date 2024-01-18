class Solution {
public:
    bool isAnagram(string s, string t) {
        int m = s.size();
        int n = t.size();
        if (m != n || s == t) {
            return false;
        }
        vector<int> cnt(26);
        for (int i = 0; i < m; ++i) {
            ++cnt[s[i] - 'a'];
            --cnt[t[i] - 'a'];
        }
        for (int x : cnt) {
            if (x) {
                return false;
            }
        }
        return true;
    }
};