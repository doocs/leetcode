class Solution {
public:
    void moveZeroes(vector<int>& nums) {
        int left = 0, n = nums.size();
        for (int right = 0; right < n; ++right) {
            if (nums[right] != 0) {
                swap(nums[left], nums[right]);
                ++left;
            }
        }
    }
};