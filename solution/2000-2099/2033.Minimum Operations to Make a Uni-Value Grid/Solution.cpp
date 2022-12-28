class Solution {
public:
    int minOperations(vector<vector<int>>& grid, int x) {
        int m = grid.size(), n = grid[0].size();
        int mod = grid[0][0] % x;
        int nums[m * n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] % x != mod) {
                    return -1;
                }
                nums[i * n + j] = grid[i][j];
            }
        }
        sort(nums, nums + m * n);
        int mid = nums[(m * n) >> 1];
        int ans = 0;
        for (int v : nums) {
            ans += abs(v - mid) / x;
        }
        return ans;
    }
};