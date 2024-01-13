class Solution {
public:
    int minFlipsMonoIncr(string s) {
        int n = s.size();
        int left0 = 0, right0 = 0;
        for (char& c : s) {
            right0 += c == '0';
        }
        int ans = min(right0, n - right0);
        for (int i = 1; i <= n; ++i) {
            int x = s[i - 1] == '1';
            right0 -= x ^ 1;
            left0 += x ^ 1;
            ans = min(ans, i - left0 + right0);
        }
        return ans;
    }
};