class Solution {
public:
    void sortColors(vector<int>& nums) {
        int i = -1, j = nums.size(), cur = 0;
        while (cur < j) {
            if (nums[cur] == 0) {
                swap(nums[++i], nums[cur++]);
            } else if (nums[cur] == 1) {
                ++cur;
            } else {
                swap(nums[cur], nums[--j]);
            }
        }
    }
};