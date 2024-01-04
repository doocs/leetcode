class Solution {
public:
    int selfDivisiblePermutationCount(int n) {
        int f[1 << (n + 1)];
        memset(f, -1, sizeof(f));
        function<int(int)> dfs = [&](int mask) {
            if (f[mask] != -1) {
                return f[mask];
            }
            int i = __builtin_popcount(mask) + 1;
            if (i > n) {
                return 1;
            }
            f[mask] = 0;
            for (int j = 1; j <= n; ++j) {
                if ((mask >> j & 1) == 0 && (i % j == 0 || j % i == 0)) {
                    f[mask] += dfs(mask | 1 << j);
                }
            }
            return f[mask];
        };
        return dfs(0);
    }
};