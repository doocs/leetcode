class Solution {
public:
    long long maxSubarraySum(vector<int>& nums, int k) {
        int n = nums.size();
        long long inf = numeric_limits<long long>::min() / 4;

        vector<array<long long, 4>> f(n + 1);

        for (int i = 0; i <= n; i++) {
            f[i].fill(inf);
        }

        f[0][0] = 0;
        long long ans = inf;

        for (int i = 1; i <= n; i++) {
            long long x = nums[i - 1];

            f[i][0] = max(f[i - 1][0], 0LL) + x;
            f[i][1] = max({f[i - 1][0], f[i - 1][1], 0LL}) + x * k;
            f[i][2] = max({f[i - 1][0], f[i - 1][2], 0LL}) + (x / k);
            f[i][3] = max({f[i - 1][1], f[i - 1][2], f[i - 1][3]}) + x;

            ans = max(ans, *max_element(f[i].begin(), f[i].end()));
        }

        return ans;
    }
};