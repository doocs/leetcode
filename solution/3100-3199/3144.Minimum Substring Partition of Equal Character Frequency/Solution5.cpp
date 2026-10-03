class Solution {
public:
    int minimumSubstringsInPartition(string s) {
        int n = s.size();
        vector<int> f(n + 1);
        for (int i = n - 1; i >= 0; --i) {
            int cnt[26]{};
            int ans = n - i;
            int k = 0, m = 0;
            for (int j = i; j < n; ++j) {
                k += ++cnt[s[j] - 'a'] == 1 ? 1 : 0;
                m = max(m, cnt[s[j] - 'a']);
                if (j - i + 1 == k * m) {
                    ans = min(ans, 1 + f[j + 1]);
                }
            }
            f[i] = ans;
        }
        return f[0];
    }
};
