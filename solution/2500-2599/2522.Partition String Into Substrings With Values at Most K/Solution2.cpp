class Solution {
public:
    int minimumPartition(string s, int k) {
        int n = s.size();
        const int inf = 1 << 30;
        vector<int> f(n + 1, inf);
        f[n] = 0;
        for (int i = n - 1; i >= 0; --i) {
            long long v = 0;
            for (int j = i; j < n; ++j) {
                v = v * 10 + (s[j] - '0');
                if (v > k) {
                    break;
                }
                f[i] = min(f[i], f[j + 1]);
            }
            ++f[i];
        }
        return f[0] < inf ? f[0] : -1;
    }
};
