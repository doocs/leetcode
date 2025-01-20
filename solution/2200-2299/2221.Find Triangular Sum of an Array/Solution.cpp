class Solution {
public:
    int triangularSum(vector<int>& nums) {
        for (int k = nums.size() - 1; k; --k) {
            for (int i = 0; i < k; ++i) {
                nums[i] = (nums[i] + nums[i + 1]) % 10;
            }
        }
        return nums[0];
    }
};
