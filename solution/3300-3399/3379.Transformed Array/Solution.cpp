class Solution {
public:
    vector<int> constructTransformedArray(vector<int>& nums) {
        int n = nums.size();
        vector<int> ans(n);
        for (int i = 0; i < n; ++i) {
            ans[i] = nums[i] ? nums[(i + nums[i] % n + n) % n] : 0;
        }
        return ans;
    }
};
