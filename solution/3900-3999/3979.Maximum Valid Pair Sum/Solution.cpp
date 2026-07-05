class Solution {
public:
    int maxValidPairSum(vector<int>& nums, int k) {
        int ans = 0;
        int x = 0;
        for (int j = k; j < nums.size(); ++j) {
            int y = nums[j];
            x = max(x, nums[j - k]);
            ans = max(ans, x + y);
        }
        return ans;
    }
};