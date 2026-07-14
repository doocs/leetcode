class Solution {
public:
    int subsequencePairCount(vector<int>& nums) {
        const int MOD = 1e9 + 7;
        int n = nums.size();
        int m = ranges::max(nums);
        vector f(n, vector(m + 1, vector<int>(m + 1, -1)));
        auto dfs = [&](this auto&& dfs, int i, int j, int k) -> int {
            if (i < 0) {
                return j == k;
            }
            int& res = f[i][j][k];
            if (res < 0) {
                res = ((dfs(i - 1, j, k)
                           + dfs(i - 1, gcd(nums[i], j), k))
                              % MOD
                          + dfs(i - 1, j, gcd(nums[i], k)))
                    % MOD;
            }
            return res;
        };
        return (dfs(n - 1, 0, 0) - 1 + MOD) % MOD;
    }
};