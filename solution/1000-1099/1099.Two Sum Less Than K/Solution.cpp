class Solution {
public:
    int twoSumLessThanK(vector<int>& nums, int k) {
        sort(nums.begin(), nums.end());
        int low = 0, high = nums.size() - 1;
        int res = -1;
        while (low < high) {
            int val = nums[low] + nums[high];
            if (val < k) {
                res = max(res, val);
                ++low;
            } else {
                --high;
            }
        }
        return res;
    }
};