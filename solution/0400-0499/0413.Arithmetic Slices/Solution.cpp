class Solution {
public:
    int numberOfArithmeticSlices(vector<int>& nums) {
        int n = nums.size();
        vector<int> dp(n, 0);
        for (int i = 2; i < n; ++i) {
            if (nums[i] + nums[i - 2] == (nums[i - 1] * 2)) {
                dp[i] = 1 + dp[i - 1];
            }
        }
        int res = 0;
        for (auto e : dp) {
            res += e;
        }
        return res;
    }
};