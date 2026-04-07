class Solution {
public:
    int minRemovals(vector<int>& nums, int target) {
        int mx = ranges::max(nums);
        int m = 0;
        while ((1 << m) <= mx) {
            ++m;
        }
        if ((1 << m) <= target) {
            return -1;
        }

        int n = nums.size();
        vector<vector<int>> f(n + 1, vector<int>(1 << m, INT_MIN));
        f[0][0] = 0;

        for (int i = 1; i <= n; i++) {
            int x = nums[i - 1];
            for (int j = 0; j < (1 << m); j++) {
                f[i][j] = max(f[i - 1][j], f[i - 1][j ^ x] + 1);
            }
        }

        if (f[n][target] < 0) {
            return -1;
        }
        return n - f[n][target];
    }
};
