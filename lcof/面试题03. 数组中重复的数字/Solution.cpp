class Solution {
public:
    int findRepeatNumber(vector<int>& nums) {
        int len = nums.size();
        for (int i = 0; i < len; i++) {
            while (i != nums[i]) {
                // 这一位的值，不等于这一位的数字
                if (nums[i] == nums[nums[i]]) {
                    // 如果在交换的过程中，发现了相等的数字，直接返回
                    return nums[i];
                }

                swap(nums[i], nums[nums[i]]);
            }
        }

        return 0;
    }
};