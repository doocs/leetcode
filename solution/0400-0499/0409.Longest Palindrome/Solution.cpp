class Solution {
public:
    int longestPalindrome(string s) {
        int cnt[128]{};
        for (char c : s) {
            ++cnt[c];
        }
        int ans = 0;
        for (int v : cnt) {
            ans += v / 2 * 2;
        }
        ans += ans < s.size();
        return ans;
    }
};