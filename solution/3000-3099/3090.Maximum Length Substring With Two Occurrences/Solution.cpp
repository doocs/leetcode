class Solution {
public:
    int maximumLengthSubstring(string s) {
        int cnt[26]{};
        int ans = 0;
        for (int i = 0, j = 0; j < s.length(); ++j) {
            int idx = s[j] - 'a';
            ++cnt[idx];
            while (cnt[idx] > 2) {
                --cnt[s[i++] - 'a'];
            }
            ans = max(ans, j - i + 1);
        }
        return ans;
    }
};