class Solution {
public:
    vector<int> leftRightDifference(vector<int>& nums) {
        int l = 0, r = 0;
        for (int x : nums) {
            r += x;
        }
        int n = nums.size();
        vector<int> ans(n);
        for (int i = 0; i < n; ++i) {
            r -= nums[i];
            ans[i] = abs(l - r);
            l += nums[i];
        }
        return ans;
    }
};
