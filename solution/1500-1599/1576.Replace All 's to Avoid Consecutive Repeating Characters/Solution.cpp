class Solution {
public:
    string modifyString(string s) {
        for (int i = 0; i < s.size(); ++i) {
            if (s[i] == '?') {
                for (char cc : "abc") {
                    if (i > 0 && s[i - 1] == cc) continue;
                    if (i < s.size() - 1 && s[i + 1] == cc) continue;
                    s[i] = cc;
                    break;
                }
            }
        }
        return s;
    }
};