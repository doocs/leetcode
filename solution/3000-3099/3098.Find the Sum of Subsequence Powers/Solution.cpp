class Solution {
public:
    int sumOfPowers(vector<int>& nums, int k) {
        unordered_map<long long, int> f;
        const int mod = 1e9 + 7;
        int n = nums.size();
        sort(nums.begin(), nums.end());
        auto dfs = [&](this auto&& dfs, int i, int j, int k, int mi) -> int {
            if (i >= n) {
                return k == 0 ? mi : 0;
            }
            if (n - i < k) {
                return 0;
            }
            long long key = (1LL * mi) << 18 | (i << 12) | (j << 6) | k;
            if (f.contains(key)) {
                return f[key];
            }
            long long ans = dfs(i + 1, j, k, mi);
            if (j == n) {
                ans += dfs(i + 1, i, k - 1, mi);
            } else {
                ans += dfs(i + 1, i, k - 1, min(mi, nums[i] - nums[j]));
            }
            ans %= mod;
            f[key] = ans;
            return f[key];
        };
        return dfs(0, n, k, INT_MAX);
    }
};
