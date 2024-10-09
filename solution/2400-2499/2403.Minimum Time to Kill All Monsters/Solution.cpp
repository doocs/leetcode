class Solution {
public:
    long long minimumTime(vector<int>& power) {
        int n = power.size();
        long long f[1 << n];
        memset(f, -1, sizeof(f));
        auto dfs = [&](auto&& dfs, int mask) -> long long {
            if (mask == 0) {
                return 0;
            }
            if (f[mask] != -1) {
                return f[mask];
            }
            f[mask] = LLONG_MAX;
            int gain = 1 + (n - __builtin_popcount(mask));
            for (int i = 0; i < n; ++i) {
                if (mask >> i & 1) {
                    f[mask] = min(f[mask], dfs(dfs, mask ^ (1 << i)) + (power[i] + gain - 1) / gain);
                }
            }
            return f[mask];
        };
        return dfs(dfs, (1 << n) - 1);
    }
};
