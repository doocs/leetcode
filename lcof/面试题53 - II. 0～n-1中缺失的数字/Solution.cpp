class Solution {
public:
    int missingNumber(vector<int>& nums) {
        int l = 0, r = nums.size();
        while (l < r) {
            int mid = (l + r) >> 1;
            if (nums[mid] > mid) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
};