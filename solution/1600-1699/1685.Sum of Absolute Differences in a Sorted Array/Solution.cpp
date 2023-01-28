class Solution {
public:
    vector<int> getSumAbsoluteDifferences(vector<int>& nums) {
        int s = accumulate(nums.begin(), nums.end(), 0), t = 0;
        int n = nums.size();
        vector<int> ans(n);
        for (int i = 0; i < n; ++i) {
            int v = nums[i] * i - t + s - t - nums[i] * (n - i);
            ans[i] = v;
            t += nums[i];
        }
        return ans;
    }
};