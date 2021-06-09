class Solution {
public:
    int maxAscendingSum(vector<int>& nums) {
        int res = 0, cur = nums[0];
        for (int i = 1; i < nums.size(); ++i) {
            if (nums[i] > nums[i - 1]) {
                cur += nums[i];
            } else {
                res = max(res, cur);
                cur = nums[i];
            }
        }
        res = max(res, cur);
        return res;
    }
};