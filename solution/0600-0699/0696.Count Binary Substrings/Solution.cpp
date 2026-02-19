class Solution {
public:
    int countBinarySubstrings(string s) {
        int n = s.size();
        int ans = 0;
        int i = 0;
        int pre = 0;
        while (i < n) {
            int j = i + 1;
            while (j < n && s[j] == s[i]) {
                ++j;
            }
            int cur = j - i;
            ans += min(pre, cur);
            pre = cur;
            i = j;
        }
        return ans;
    }
};
