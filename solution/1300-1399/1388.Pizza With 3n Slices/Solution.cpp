class Solution {
public:
    int maxSizeSlices(vector<int>& slices) {
        int n = slices.size() / 3;
        auto g = [&](vector<int>& nums) -> int {
            int m = nums.size();
            int f[m + 1][n + 1];
            memset(f, 0, sizeof f);
            for (int i = 1; i <= m; ++i) {
                for (int j = 1; j <= n; ++j) {
                    f[i][j] = max(f[i - 1][j], (i >= 2 ? f[i - 2][j - 1] : 0) + nums[i - 1]);
                }
            }
            return f[m][n];
        };
        vector<int> nums(slices.begin(), slices.end() - 1);
        int a = g(nums);
        nums = vector<int>(slices.begin() + 1, slices.end());
        int b = g(nums);
        return max(a, b);
    }
};