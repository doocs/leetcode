class Solution {
public:
    int maxScore(string s) {
        int t = 0;
        if (s[0] == '0') ++t;
        for (int i = 1; i < s.size(); ++i) t += s[i] == '1';
        int ans = t;
        for (int i = 1; i < s.size() - 1; ++i) {
            t += s[i] == '0' ? 1 : -1;
            ans = max(ans, t);
        }
        return ans;
    }
};