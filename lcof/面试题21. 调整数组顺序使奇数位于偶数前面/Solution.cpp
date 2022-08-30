class Solution {
public:
    vector<int> exchange(vector<int>& nums) {
        int i = 0, j = nums.size() - 1;
        while (i < j) {
            while (i < j && nums[i] % 2 == 1) {
                ++i;
            }
            while (i < j && nums[j] % 2 == 0) {
                --j;
            }
            swap(nums[i], nums[j]);
        }
        return nums;
    }
};