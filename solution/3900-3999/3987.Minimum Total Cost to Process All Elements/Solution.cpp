class Solution {
public:
    int minimumCost(vector<int>& nums, int k) {
        const int MOD = 1'000'000'007;
        long long cnt = 0;
        long long cur = k;

        for (int x : nums) {
            long long diff = (long long) x - cur;
            if (diff > 0) {
                long long m = (diff + k - 1LL) / k;
                cur += m * k;
                cnt += m;
            }
            cur -= x;
        }

        cnt %= MOD;
        return (cnt + 1) * cnt / 2 % MOD;
    }
};