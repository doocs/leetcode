class Solution {
public:
    int longestSubarray(vector<int>& nums) {
        int f = 2;
        int ans = f;
        for (int i = 2; i < nums.size(); ++i) {
            if (nums[i] == nums[i - 1] + nums[i - 2]) {
                ++f;
                ans = max(ans, f);
            } else {
                f = 2;
            }
        }
        return ans;
    }
};
