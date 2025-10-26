class Solution {
public:
    long long maxAlternatingSum(vector<int>& nums) {
        for (int& x : nums) {
            x = x * x;
        }
        ranges::sort(nums);
        long long ans = 0, m = nums.size() / 2;
        for (int i = 0; i < m; ++i) {
            ans -= nums[i];
        }
        for (int i = m; i < nums.size(); ++i) {
            ans += nums[i];
        }
        return ans;
    }
};
