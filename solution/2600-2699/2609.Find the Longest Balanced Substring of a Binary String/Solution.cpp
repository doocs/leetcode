class Solution {
public:
    int findTheLongestBalancedSubstring(string s) {
        int zero = 0, one = 0;
        int ans = 0, n = s.size();
        for (char& c : s) {
            if (c == '0') {
                if (one > 0) {
                    zero = 0;
                    one = 0;
                }
                ++zero;
            } else {
                ans = max(ans, 2 * min(zero, ++one));
            }
        }
        return ans;
    }
};