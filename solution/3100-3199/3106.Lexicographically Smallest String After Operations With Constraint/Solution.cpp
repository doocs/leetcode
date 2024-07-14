class Solution {
public:
    string getSmallestString(string s, int k) {
        for (int i = 0; i < s.size(); ++i) {
            char c1 = s[i];
            for (char c2 = 'a'; c2 < c1; ++c2) {
                int d = min(c1 - c2, 26 - c1 + c2);
                if (d <= k) {
                    s[i] = c2;
                    k -= d;
                    break;
                }
            }
        }
        return s;
    }
};