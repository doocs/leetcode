class Solution {
public:
    int maxProduct(vector<int>& nums) {
        int maxf = nums[0], minf = nums[0], res = nums[0];
        for (int i = 1; i < nums.size(); ++i) {
            int m = maxf, n = minf;
            maxf = max(nums[i], max(nums[i] * m, nums[i] * n));
            minf = min(nums[i], min(nums[i] * m, nums[i] * n));
            res = max(res, maxf);
        }
        return res;
    }
};