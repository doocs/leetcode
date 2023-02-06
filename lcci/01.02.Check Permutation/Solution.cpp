class Solution {
public:
    bool CheckPermutation(string s1, string s2) {
        if (s1.size() != s2.size()) return false;
        int cnt[26] = {0};
        for (char& c : s1) ++cnt[c - 'a'];
        for (char& c : s2)
            if (--cnt[c - 'a'] < 0) return false;
        return true;
    }
};