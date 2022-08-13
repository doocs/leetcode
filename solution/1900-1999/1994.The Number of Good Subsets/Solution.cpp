class Solution {
public:
    int numberOfGoodSubsets(vector<int>& nums) {
        vector<int> counter(31);
        for (int& x : nums) ++counter[x];
        vector<int> prime = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29};
        const int MOD = 1e9 + 7;
        int n = prime.size();
        vector<long long> dp(1 << n);
        dp[0] = 1;
        for (int x = 2; x <= 30; ++x) {
            if (counter[x] == 0 || x % 4 == 0 || x % 9 == 0 || x % 25 == 0) continue;
            int mask = 0;
            for (int i = 0; i < n; ++i)
                if (x % prime[i] == 0)
                    mask |= (1 << i);
            for (int state = 0; state < 1 << n; ++state) {
                if ((mask & state) > 0) continue;
                dp[mask | state] = (dp[mask | state] + counter[x] * dp[state]) % MOD;
            }
        }
        long long ans = 0;
        for (int i = 1; i < 1 << n; ++i) ans = (ans + dp[i]) % MOD;
        while (counter[1]--) ans = (ans << 1) % MOD;
        return (int)ans;
    }
};