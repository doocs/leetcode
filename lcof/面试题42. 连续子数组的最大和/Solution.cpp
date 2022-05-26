class Solution {
public:
    int maxSubArray(vector<int>& nums) {
        int res = nums[0], f = nums[0];
        for (int i = 1; i < nums.size(); ++i) {
            f = max(f, 0) + nums[i];
            res = max(res, f);
        }
        return res;
    }
};