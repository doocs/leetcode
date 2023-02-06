class Solution {
public:
    const int mod = 1e9 + 7;

    int waysToSplit(vector<int>& nums) {
        int n = nums.size();
        vector<int> s(n, nums[0]);
        for (int i = 1; i < n; ++i) s[i] = s[i - 1] + nums[i];
        int ans = 0;
        for (int i = 0; i < n - 2; ++i) {
            int j = lower_bound(s.begin() + i + 1, s.begin() + n - 1, s[i] << 1) - s.begin();
            int k = upper_bound(s.begin() + j, s.begin() + n - 1, (s[n - 1] + s[i]) >> 1) - s.begin();
            ans = (ans + k - j) % mod;
        }
        return ans;
    }
};