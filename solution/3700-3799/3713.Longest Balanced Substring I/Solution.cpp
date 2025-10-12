class Solution {
public:
    int longestBalanced(string s) {
        int n = s.size();
        vector<int> cnt(26, 0);
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            fill(cnt.begin(), cnt.end(), 0);
            int mx = 0, v = 0;
            for (int j = i; j < n; ++j) {
                int c = s[j] - 'a';
                if (++cnt[c] == 1) {
                    ++v;
                }
                mx = max(mx, cnt[c]);
                if (mx * v == j - i + 1) {
                    ans = max(ans, j - i + 1);
                }
            }
        }
        return ans;
    }
};
