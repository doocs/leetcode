class Solution {
public:
    int peakValleyDifference(vector<int>& nums) {
        int ans = 0;
        for (int i = 1; i < nums.size() - 1; i++) {
            if (nums[i - 1] < nums[i] && nums[i] > nums[i + 1]) {
                ans += nums[i];
            } else if (nums[i - 1] > nums[i] && nums[i] < nums[i + 1]) {
                ans -= nums[i];
            }
        }
        return ans;
    }
};