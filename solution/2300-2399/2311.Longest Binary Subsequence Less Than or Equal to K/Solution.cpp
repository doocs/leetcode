class Solution {
public:
    int longestSubsequence(string s, int k) {
        int ans = 0;
        long long v = 0;
        for (int i = s.size() - 1; ~i; --i) {
            if (s[i] == '0')
                ++ans;
            else if (ans < 32 && v + (1ll << ans) <= k) {
                v += 1ll << ans;
                ++ans;
            }
        }
        return ans;
    }
};