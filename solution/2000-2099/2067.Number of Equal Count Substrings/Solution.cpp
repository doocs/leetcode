class Solution {
public:
    int equalCountSubstrings(string s, int count) {
        int ans = 0;
        int n = s.size();
        int cnt[26];
        for (int i = 1; i < 27 && i * count <= n; ++i) {
            int k = i * count;
            memset(cnt, 0, sizeof(cnt));
            int t = 0;
            for (int j = 0; j < n; ++j) {
                int a = s[j] - 'a';
                t += ++cnt[a] == count;
                t -= cnt[a] == count + 1;
                if (j >= k) {
                    int b = s[j - k] - 'a';
                    t += --cnt[b] == count;
                    t -= cnt[b] == count - 1;
                }
                ans += i == t;
            }
        }
        return ans;
    }
};