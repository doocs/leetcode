class Solution {
public:
    vector<int> twoSum(vector<int>& nums, int target) {
        int l = 0, r = nums.size() - 1;
        while (1) {
            if (nums[l] + nums[r] == target) {
                return {nums[l], nums[r]};
            }
            if (nums[l] + nums[r] > target) {
                --r;
            } else {
                ++l;
            }
        }
    }
};