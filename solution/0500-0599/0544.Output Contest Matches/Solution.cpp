class Solution {
public:
    string findContestMatch(int n) {
        vector<string> s(n);
        for (int i = 0; i < n; ++i) {
            s[i] = to_string(i + 1);
        }
        for (; n > 1; n >>= 1) {
            for (int i = 0; i < n >> 1; ++i) {
                s[i] = "(" + s[i] + "," + s[n - i - 1] + ")";
            }
        }
        return s[0];
    }
};