class Solution {
public:
    int sumOfGoodSubsequences(vector<int>& nums) {
        const int mod = 1e9 + 7;
        int mx = ranges::max(nums);

        vector<long long> f(mx + 1), g(mx + 1);
        for (int x : nums) {
            f[x] += x;
            g[x] += 1;

            if (x > 0) {
                f[x] = (f[x] + f[x - 1] + g[x - 1] * x % mod) % mod;
                g[x] = (g[x] + g[x - 1]) % mod;
            }

            if (x + 1 <= mx) {
                f[x] = (f[x] + f[x + 1] + g[x + 1] * x % mod) % mod;
                g[x] = (g[x] + g[x + 1]) % mod;
            }
        }

        return accumulate(f.begin(), f.end(), 0LL) % mod;
    }
};
