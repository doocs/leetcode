class Solution {
public:
    vector<int> sortArrayByParity(vector<int>& nums) {
        for (int i = 0, j = nums.size() - 1; i < j;) {
            if (nums[i] & 1)
                swap(nums[i], nums[j--]);
            else
                ++i;
        }
        return nums;
    }
};