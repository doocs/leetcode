class Solution {
public:
    int selfDivisiblePermutationCount(int n) {
        int f[1 << n];
        memset(f, 0, sizeof(f));
        f[0] = 1;
        for (int mask = 0; mask < 1 << n; ++mask) {
            int i = __builtin_popcount(mask);
            for (int j = 1; j <= n; ++j) {
                if (((mask >> (j - 1)) & 1) == 1 && (i % j == 0 || j % i == 0)) {
                    f[mask] += f[mask ^ (1 << (j - 1))];
                }
            }
        }
        return f[(1 << n) - 1];
    }
};