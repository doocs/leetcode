class Solution {
public:
    int findRepeatNumber(vector<int>& nums) {
        sort(nums.begin(), nums.end());
        for (int i = 0;; ++i) {
            if (nums[i] == nums[i + 1]) {
                return nums[i];
            }
        }
    }
};