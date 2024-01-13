class Solution {
public:
    int findRepeatNumber(vector<int>& nums) {
        for (int i = 0;; ++i) {
            while (nums[i] != i) {
                int j = nums[i];
                if (nums[j] == j) {
                    return j;
                }
                swap(nums[i], nums[j]);
            }
        }
    }
};