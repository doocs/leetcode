class Solution {
public:
    int maxSubArray(vector<int>& nums) {
        int f = nums[0], res = nums[0];
        for (int i = 1; i < nums.size(); ++i) {
            f = nums[i] + max(f, 0);
            res = max(res, f);
        }
        return res;
    }
};