class Solution {
public:
    int lengthOfLongestSubstring(string s) {
        bool ss[128] = {false};
        int n = s.size();
        int ans = 0;
        for (int i = 0, j = 0; i < n; ++i) {
            while (ss[s[i]]) {
                ss[s[j++]] = false;
            }
            ss[s[i]] = true;
            ans = max(ans, i - j + 1);
        }
        return ans;
    }
};