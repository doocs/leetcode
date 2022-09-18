class Solution {
public:
    int longestContinuousSubstring(string s) {
        int ans = 0;
        int i = 0, j = 1;
        for (; j < s.size(); ++j) {
            ans = max(ans, j - i);
            if (s[j] - s[j - 1] != 1) {
                i = j;
            }
        }
        ans = max(ans, j - i);
        return ans;
    }
};