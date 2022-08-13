class Solution {
public:
    bool isAnagram(string s, string t) {
        if (s.size() != t.size() || s == t)
            return false;
        vector<int> chars(26, 0);
        for (int i = 0, n = s.size(); i < n; ++i) {
            ++chars[s[i] - 'a'];
            --chars[t[i] - 'a'];
        }
        for (int c : chars) {
            if (c != 0)
                return false;
        }
        return true;
    }
};