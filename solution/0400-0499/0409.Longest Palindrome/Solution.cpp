class Solution {
public:
    int longestPalindrome(string s) {
        int cnt[128]{};
        for (char& c : s) {
            ++cnt[c];
        }
        int ans = 0;
        for (int v : cnt) {
            ans += v - (v & 1);
            if (ans % 2 == 0 && v % 2 == 1) {
                ++ans;
            }
        }
        return ans;
    }
};