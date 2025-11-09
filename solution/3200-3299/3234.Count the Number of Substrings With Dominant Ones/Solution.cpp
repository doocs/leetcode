class Solution {
public:
    int numberOfSubstrings(string s) {
        int n = s.size();
        vector<int> nxt(n + 1);
        nxt[n] = n;
        for (int i = n - 1; i >= 0; --i) {
            nxt[i] = nxt[i + 1];
            if (s[i] == '0') {
                nxt[i] = i;
            }
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            int cnt0 = s[i] == '0' ? 1 : 0;
            int j = i;
            while (j < n && 1LL * cnt0 * cnt0 <= n) {
                int cnt1 = nxt[j + 1] - i - cnt0;
                if (cnt1 >= cnt0 * cnt0) {
                    ans += min(nxt[j + 1] - j, cnt1 - cnt0 * cnt0 + 1);
                }
                j = nxt[j + 1];
                ++cnt0;
            }
        }
        return ans;
    }
};
