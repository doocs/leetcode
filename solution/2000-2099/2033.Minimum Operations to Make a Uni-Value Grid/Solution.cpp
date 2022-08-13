class Solution {
public:
    int minOperations(vector<vector<int>>& grid, int x) {
        vector<int> nums;
        int m = grid.size(), n = grid[0].size();
        int base = grid[0][0];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (abs(grid[i][j] - base) % x != 0) return -1;
                nums.push_back(grid[i][j]);
            }
        }
        sort(nums.begin(), nums.end());
        int mid = nums[nums.size() >> 1];
        int ans = 0;
        for (int num : nums) ans += abs(num - mid) / x;
        return ans;
    }
};