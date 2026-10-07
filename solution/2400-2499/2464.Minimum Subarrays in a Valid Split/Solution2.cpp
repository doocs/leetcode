class Solution {
public:
    int validSubarraySplit(vector<int>& nums) {
        int n = nums.size();
        const int inf = 0x3f3f3f3f;
        vector<int> f(n + 1, inf);
        f[n] = 0;
        for (int i = n - 1; i >= 0; --i) {
            for (int j = i; j < n; ++j) {
                if (__gcd(nums[i], nums[j]) > 1) {
                    f[i] = min(f[i], 1 + f[j + 1]);
                }
            }
        }
        return f[0] < inf ? f[0] : -1;
    }
};
