class Solution {
public:
    int rob(vector<int>& nums) {
        int n = nums.size();
        int f[n];
        memset(f, -1, sizeof(f));
        auto dfs = [&](auto&& dfs, int i) -> int {
            if (i >= n) {
                return 0;
            }
            if (f[i] < 0) {
                f[i] = max(nums[i] + dfs(dfs, i + 2), dfs(dfs, i + 1));
            }
            return f[i];
        };
        return dfs(dfs, 0);
    }
};
