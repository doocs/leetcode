class Solution {
public:
    int findMin(vector<int>& nums) {
        int l = 0, r = nums.size() - 1;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (nums[mid] > nums.back()) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return nums[l];
    }
};
