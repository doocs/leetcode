class Solution {
public:
    int sumOfGoodIntegers(long long n, long long k) {
        const long long MOD = 1'000'000'007;
        long long num;
        vector<vector<array<long long, 2>>> memo;

        auto calc = [&](long long x, int m) -> long long {
            num = x;
            memo.assign(m, vector<array<long long, 2>>(2, {-1, -1}));

            auto dfs = [&](this auto&& dfs, int i, bool lim) -> pair<long long, long long> {
                if (i < 0) {
                    return {1, 0};
                }
                int j = lim ? 1 : 0;
                if (memo[i][j][0] != -1) {
                    return {memo[i][j][0], memo[i][j][1]};
                }
                long long up = lim ? (num >> i & 1) : 1;
                long long cnt = 0, s = 0;
                for (int cur = 0; cur <= up; cur++) {
                    if ((n >> i & 1) == 1 && cur == 1) {
                        continue;
                    }
                    auto [cnt1, s1] = dfs(i - 1, lim && cur == up);
                    cnt = (cnt + cnt1) % MOD;
                    s = (s + s1 + cnt1 * ((1LL << i) % MOD) % MOD * cur) % MOD;
                }
                memo[i][j] = {cnt, s};
                return {cnt, s};
            };

            return dfs(m - 1, true).second;
        };

        long long lo = max(1LL, n - k), hi = n + k;
        int m = 64 - __builtin_clzll(hi);
        long long right = calc(hi, m);
        long long left = calc(lo - 1, m);
        return (right - left + MOD) % MOD;
    }
};