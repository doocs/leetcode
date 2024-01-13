class Solution {
public:
    bool isCircularSentence(string s) {
        int n = s.size();
        if (s[0] != s.back()) {
            return false;
        }
        for (int i = 1; i < n; ++i) {
            if (s[i] == ' ' && s[i - 1] != s[i + 1]) {
                return false;
            }
        }
        return true;
    }
};