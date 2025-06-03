class Solution {
public:
    string shortestSuperstring(string s1, string s2) {
        int m = s1.size(), n = s2.size();
        if (m > n) {
            return shortestSuperstring(s2, s1);
        }
        if (s2.find(s1) != string::npos) {
            return s2;
        }
        for (int i = 0; i < m; ++i) {
            if (s2.find(s1.substr(i)) == 0) {
                return s1.substr(0, i) + s2;
            }
            if (s2.rfind(s1.substr(0, m - i)) == s2.size() - (m - i)) {
                return s2 + s1.substr(m - i);
            }
        }
        return s1 + s2;
    }
};
