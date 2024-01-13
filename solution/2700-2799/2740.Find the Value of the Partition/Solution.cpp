class Solution {
public:
    int findValueOfPartition(vector<int>& nums) {
        sort(nums.begin(), nums.end());
        int ans = 1 << 30;
        for (int i = 1; i < nums.size(); ++i) {
            ans = min(ans, nums[i] - nums[i - 1]);
        }
        return ans;
    }
};