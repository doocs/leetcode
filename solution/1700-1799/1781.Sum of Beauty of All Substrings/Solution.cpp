class Solution {
public:
    int beautySum(string s) {
        int ans = 0;
        int n = s.size();
        int cnt[26];
        for (int i = 0; i < n; ++i) {
            memset(cnt, 0, sizeof cnt);
            for (int j = i; j < n; ++j) {
                ++cnt[s[j] - 'a'];
                int mi = 1000, mx = 0;
                for (int& v : cnt) {
                    if (v > 0) {
                        mi = min(mi, v);
                        mx = max(mx, v);
                    }
                }
                ans += mx - mi;
            }
        }
        return ans;
    }
};