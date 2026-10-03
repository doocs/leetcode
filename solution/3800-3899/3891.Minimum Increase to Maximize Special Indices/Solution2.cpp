class Solution {
public:
    long long minIncrease(vector<int>& nums) {
        int n = nums.size();
        vector<array<long long, 2>> f(n + 1);
        for (int i = n - 2; i >= 1; --i) {
            long long cost = max(0, max(nums[i - 1], nums[i + 1]) + 1 - nums[i]);
            f[i][0] = cost + f[i + 2][0];
            f[i][1] = min(cost + f[i + 2][1], f[i + 1][0]);
        }
        return f[1][(n & 1) ^ 1];
    }
};
