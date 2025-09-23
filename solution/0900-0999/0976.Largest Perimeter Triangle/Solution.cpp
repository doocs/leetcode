class Solution {
public:
    int largestPerimeter(vector<int>& nums) {
        ranges::sort(nums);
        for (int i = nums.size() - 1; i > 1; --i) {
            int c = nums[i - 1] + nums[i - 2];
            if (c > nums[i]) {
                return c + nums[i];
            }
        }
        return 0;
    }
};
