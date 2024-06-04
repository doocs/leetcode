class Solution {
public:
    int characterReplacement(string s, int k) {
        int cnt[26]{};
        int l = 0, mx = 0;
        int n = s.length();
        for (int r = 0; r < n; ++r) {
            mx = max(mx, ++cnt[s[r] - 'A']);
            if (r - l + 1 - mx > k) {
                --cnt[s[l++] - 'A'];
            }
        }
        return n - l;
    }
};