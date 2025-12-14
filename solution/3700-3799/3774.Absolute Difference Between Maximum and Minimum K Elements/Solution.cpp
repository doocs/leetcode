class Solution {
public:
    int absDifference(vector<int>& nums, int k) {
        ranges::sort(nums);
        int n = nums.size();
        int ans = 0;
        for (int i = 0; i < k; ++i) {
            ans += nums[n - i - 1] - nums[i];
        }
        return ans;
    }
};
