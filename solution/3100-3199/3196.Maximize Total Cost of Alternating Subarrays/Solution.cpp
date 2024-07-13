class Solution {
public:
    long long maximumTotalCost(vector<int>& nums) {
        int n = nums.size();
        long long f[n][2];
        fill(f[0], f[n], LLONG_MIN);
        auto dfs = [&](auto&& dfs, int i, int j) -> long long {
            if (i >= n) {
                return 0;
            }
            if (f[i][j] != LLONG_MIN) {
                return f[i][j];
            }
            f[i][j] = nums[i] + dfs(dfs, i + 1, 1);
            if (j) {
                f[i][j] = max(f[i][j], -nums[i] + dfs(dfs, i + 1, 0));
            }
            return f[i][j];
        };
        return dfs(dfs, 0, 0);
    }
};