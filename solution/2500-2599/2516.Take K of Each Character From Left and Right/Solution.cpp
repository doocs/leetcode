class Solution {
public:
    int takeCharacters(string s, int k) {
        int cnt[3] = {0};
        for (char& c : s) ++cnt[c - 'a'];
        if (cnt[0] < k || cnt[1] < k || cnt[2] < k) return -1;
        int ans = 0, j = 0;
        int n = s.size();
        for (int i = 0; i < n; ++i) {
            int c = s[i] - 'a';
            --cnt[c];
            while (cnt[c] < k) {
                ++cnt[s[j++] - 'a'];
            }
            ans = max(ans, i - j + 1);
        }
        return n - ans;
    }
};