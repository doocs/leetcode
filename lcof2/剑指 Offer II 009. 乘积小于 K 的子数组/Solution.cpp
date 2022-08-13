class Solution {
public:
    int numSubarrayProductLessThanK(vector<int>& nums, int k) {
        int left = 0, right;
        long mul = 1;
        int count = 0;

        for (right = 0; right < nums.size(); right++) {
            mul *= nums[right];

            while (left <= right && mul >= k) {
                mul /= nums[left++];
            }

            count += right >= left ? right - left + 1 : 0;
        }

        return count;
    }
};