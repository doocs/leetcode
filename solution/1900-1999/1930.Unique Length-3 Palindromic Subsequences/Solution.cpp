class Solution {
public:
    int countPalindromicSubsequence(string s) {
        int ans = 0;
        for (char c = 'a'; c <= 'z'; ++c) {
            int l = s.find_first_of(c), r = s.find_last_of(c);
            int mask = 0;
            for (int i = l + 1; i < r; ++i) {
                int j = s[i] - 'a';
                if (mask >> j & 1 ^ 1) {
                    mask |= 1 << j;
                    ++ans;
                }
            }
        }
        return ans;
    }
};
