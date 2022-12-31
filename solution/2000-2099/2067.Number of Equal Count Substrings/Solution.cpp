class Solution {
public:
    int equalCountSubstrings(string s, int count) {
        int ans = 0;
        int n = s.size();
        int cnt[26];
        for (int x = 1; x < 27 && count * x <= n; ++x) {
            int m = count * x;
            memset(cnt, 0, sizeof cnt);
            int y = 0;
            for (int i = 0; i < n; ++i) {
                int a = s[i] - 'a';
                ++cnt[a];
                y += cnt[a] == count;
                y -= cnt[a] == count + 1;
                int j = i - m;
                if (j >= 0) {
                    int b = s[j] - 'a';
                    --cnt[b];
                    y += cnt[b] == count;
                    y -= cnt[b] == count - 1;
                }
                ans += x == y;
            }
        }
        return ans;
    }
};