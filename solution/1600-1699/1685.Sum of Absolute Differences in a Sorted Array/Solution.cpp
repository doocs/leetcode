class Solution {
public:
    vector<int> getSumAbsoluteDifferences(vector<int>& nums) {
        int s = accumulate(nums.begin(), nums.end(), 0);
        int t = 0, n = nums.size();
        vector<int> ans(n);
        for (int i = 0; i < n; ++i) {
            int v = nums[i];
            int x = s - t - (n - i) * v + v * i - t;
            t += v;
            ans[i] = x;
        }
        return ans;
    }
};