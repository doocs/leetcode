class Solution {
public:
    int sumOfPower(vector<int>& nums) {
        const int mod = 1e9 + 7;
        sort(nums.rbegin(), nums.rend());
        long long ans = 0, p = 0;
        for (long long x : nums) {
            ans = (ans + (x * x % mod) * x) % mod;
            ans = (ans + x * p % mod) % mod;
            p = (p * 2 + x * x % mod) % mod;
        }
        return ans;
    }
};