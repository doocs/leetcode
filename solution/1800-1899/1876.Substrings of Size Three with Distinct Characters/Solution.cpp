class Solution {
public:
    int countGoodSubstrings(string s) {
        int ans = 0;
        int n = s.length();
        for (int l = 0, r = 0, mask = 0; r < n; ++r) {
            int x = s[r] - 'a';
            while ((mask >> x & 1) == 1) {
                int y = s[l++] - 'a';
                mask ^= 1 << y;
            }
            mask |= 1 << x;
            ans += r - l + 1 >= 3 ? 1 : 0;
        }
        return ans;
    }
};
