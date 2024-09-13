class Solution {
public:
    long long minimumTime(vector<int>& power) {
        int n = power.size();
        long long f[1 << n];
        memset(f, 0x3f, sizeof(f));
        f[0] = 0;
        for (int mask = 1; mask < 1 << n; ++mask) {
            int gain = __builtin_popcount(mask);
            for (int i = 0; i < n; ++i) {
                if (mask >> i & 1) {
                    f[mask] = min(f[mask], f[mask ^ (1 << i)] + (power[i] + gain - 1) / gain);
                }
            }
        }
        return f[(1 << n) - 1];
    }
};
