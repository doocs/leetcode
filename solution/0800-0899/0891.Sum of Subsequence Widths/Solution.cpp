class Solution {
public:
    const int mod = 1e9 + 7;

    int sumSubseqWidths(vector<int>& nums) {
        sort(nums.begin(), nums.end());
        long ans = 0, p = 1;
        int n = nums.size();
        for (int i = 0; i < n; ++i) {
            ans = (ans + (nums[i] - nums[n - i - 1]) * p + mod) % mod;
            p = (p << 1) % mod;
        }
        return ans;
    }
};