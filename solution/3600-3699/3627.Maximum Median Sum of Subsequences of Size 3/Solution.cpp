class Solution {
public:
    long long maximumMedianSum(vector<int>& nums) {
        ranges::sort(nums);
        int n = nums.size();
        long long ans = 0;
        for (int i = n / 3; i < n; i += 2) {
            ans += nums[i];
        }
        return ans;
    }
};