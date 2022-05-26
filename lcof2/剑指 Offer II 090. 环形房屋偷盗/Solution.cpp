class Solution {
public:
    int rob(vector<int>& nums) {
        if (nums.size() == 1) {
            return nums[0];
        }
        return max(_rob(nums, 0, nums.size() - 1), _rob(nums, 1, nums.size()));
    }

    int _rob(vector<int>& nums, int start, int end) {
        if (start + 1 == end) {
            return nums[start];
        }
        int n = end - start;
        vector<int> dp(n, 0);
        dp[0] = nums[start];
        dp[1] = max(nums[start], nums[start + 1]);
        for (int i = 2; i < n; ++i) {
            dp[i] = max(dp[i - 2] + nums[start + i], dp[i - 1]);
        }
        return dp[n - 1];
    }
};
