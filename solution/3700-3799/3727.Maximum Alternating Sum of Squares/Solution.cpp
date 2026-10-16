class Solution {
public:
    long long maxAlternatingSum(vector<int>& nums) {
        for (int& x : nums) {
            x = x * x;
        }
        ranges::sort(nums);
        long long ans = 0;
        int m = nums.size() >> 1;
        for (int i = 0; i < nums.size(); ++i) {
            ans += i < m ? -nums[i] : nums[i];
        }
        return ans;
    }
};
