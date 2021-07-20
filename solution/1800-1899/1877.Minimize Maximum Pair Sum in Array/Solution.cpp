class Solution {
public:
    int minPairSum(vector<int>& nums) {
        sort(nums.begin(), nums.end());
        int res = 0, n = nums.size();
        for (int i = 0; i < (n >> 1); ++i) {
            res = max(res, nums[i] + nums[n - i - 1]);
        }
        return res;
    }
};