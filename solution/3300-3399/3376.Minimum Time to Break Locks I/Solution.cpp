class Solution {
public:
    int findMinimumTime(vector<int>& strength, int K) {
        int n = strength.size();
        int f[1 << n];
        memset(f, -1, sizeof(f));
        int k = K;
        auto dfs = [&](this auto&& dfs, int i) -> int {
            if (i == (1 << n) - 1) {
                return 0;
            }
            if (f[i] != -1) {
                return f[i];
            }
            int cnt = __builtin_popcount(i);
            int x = 1 + k * cnt;
            f[i] = INT_MAX;
            for (int j = 0; j < n; ++j) {
                if (i >> j & 1 ^ 1) {
                    f[i] = min(f[i], dfs(i | 1 << j) + (strength[j] + x - 1) / x);
                }
            }
            return f[i];
        };
        return dfs(0);
    }
};
