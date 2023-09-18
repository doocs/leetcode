class Solution {
public:
    long long maximumSum(vector<int>& nums) {
        long long ans = 0;
        int n = nums.size();
        for (int k = 1; k <= n; ++k) {
            long long t = 0;
            for (int j = 1; k * j * j <= n; ++j) {
                t += nums[k * j * j - 1];
            }
            ans = max(ans, t);
        }
        return ans;
    }
};