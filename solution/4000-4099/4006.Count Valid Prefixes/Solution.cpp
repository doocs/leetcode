class Solution {
public:
    int countValidPrefixes(string s) {
        int ans = 0, t = 0;
        for (char c : s) {
            t += c == '1' ? 1 : -1;
            if (abs(t) <= 1) {
                ans++;
            }
        }
        return ans;
    }
};