class Solution {
public:
    int waysToSplit(vector<int>& nums) {
        int n = nums.size();
        vector<int> s(n, nums[0]);
        for (int i = 1; i < n; ++i) s[i] = s[i - 1] + nums[i];
        int ans = 0;
        int mod = 1e9 + 7;
        for (int i = 0; i < n - 2; ++i) {
            int j0 = lower_bound(s.begin() + i + 1, s.begin() + n - 1, s[i] * 2) - s.begin();
            int j1 = upper_bound(s.begin() + j0, s.begin() + n - 1, (s[i] + s[n - 1]) / 2) - s.begin();
            ans = (ans + j1 - j0) % mod;
        }
        return ans;
    }
};