class Solution {
public:
    int minimumSubstringsInPartition(string s) {
        int n = s.size();
        vector<int> f(n + 1, n);
        f[0] = 0;
        for (int i = 0; i < n; ++i) {
            int cnt[26]{};
            int k = 0, m = 0;
            for (int j = i; ~j; --j) {
                k += ++cnt[s[j] - 'a'] == 1;
                m = max(m, cnt[s[j] - 'a']);
                if (i - j + 1 == k * m) {
                    f[i + 1] = min(f[i + 1], f[j] + 1);
                }
            }
        }
        return f[n];
    }
};
