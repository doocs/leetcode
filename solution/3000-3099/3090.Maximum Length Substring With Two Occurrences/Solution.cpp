class Solution {
public:
    int maximumLengthSubstring(string s) {
        int ans = 0;
        int cnt[26]{};
        for (int l = 0, r = 0; r < s.size(); ++r) {
            int idx = s[r] - 'a';
            ++cnt[idx];
            while (cnt[idx] > 2) {
                --cnt[s[l++] - 'a'];
            }
            ans = max(ans, r - l + 1);
        }
        return ans;
    }
};