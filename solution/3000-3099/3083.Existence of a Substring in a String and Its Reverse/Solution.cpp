class Solution {
public:
    bool isSubstringPresent(string s) {
        bool st[26][26]{};
        int n = s.size();
        for (int i = 0; i < n - 1; ++i) {
            st[s[i + 1] - 'a'][s[i] - 'a'] = true;
        }
        for (int i = 0; i < n - 1; ++i) {
            if (st[s[i] - 'a'][s[i + 1] - 'a']) {
                return true;
            }
        }
        return false;
    }
};