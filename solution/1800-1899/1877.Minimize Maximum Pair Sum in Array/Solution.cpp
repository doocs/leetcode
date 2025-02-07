class Solution {
public:
    int minPairSum(vector<int>& nums) {
        ranges::sort(nums);
        int ans = 0, n = nums.size();
        for (int i = 0; i < n >> 1; ++i) {
            ans = max(ans, nums[i] + nums[n - i - 1]);
        }
        return ans;
    }
};
