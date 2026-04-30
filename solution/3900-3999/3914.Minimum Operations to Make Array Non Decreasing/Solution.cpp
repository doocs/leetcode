class Solution {
public:
    long long minOperations(vector<int>& nums) {
        long long ans = 0;
        for (int i = 1; i < nums.size(); ++i) {
            ans += max(nums[i - 1] - nums[i], 0);
        }
        return ans;
    }
};
