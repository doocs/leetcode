class Solution {
public:
    int rob(vector<int>& nums) {
        int n = nums.size();
        if (n == 1) {
            return nums[0];
        }
        return max(robRange(nums, 0, n - 2), robRange(nums, 1, n - 1));
    }

    int robRange(vector<int>& nums, int l, int r) {
        int f = 0, g = 0;
        for (; l <= r; ++l) {
            int ff = max(f, g);
            g = f + nums[l];
            f = ff;
        }
        return max(f, g);
    }
};