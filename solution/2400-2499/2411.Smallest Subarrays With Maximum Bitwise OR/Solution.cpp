class Solution {
public:
    vector<int> smallestSubarrays(vector<int>& nums) {
        int n = nums.size();
        vector<int> f(32, -1);
        vector<int> ans(n);
        for (int i = n - 1; ~i; --i) {
            int t = 1;
            for (int j = 0; j < 32; ++j) {
                if ((nums[i] >> j) & 1) {
                    f[j] = i;
                } else if (f[j] != -1) {
                    t = max(t, f[j] - i + 1);
                }
            }
            ans[i] = t;
        }
        return ans;
    }
};