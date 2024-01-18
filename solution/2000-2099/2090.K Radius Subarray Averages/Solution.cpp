class Solution {
public:
    vector<int> getAverages(vector<int>& nums, int k) {
        int n = nums.size();
        long s[n + 1];
        s[0] = 0;
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + nums[i];
        }
        vector<int> ans(n, -1);
        for (int i = 0; i < n; ++i) {
            if (i - k >= 0 && i + k < n) {
                ans[i] = (s[i + k + 1] - s[i - k]) / (k << 1 | 1);
            }
        }
        return ans;
    }
};