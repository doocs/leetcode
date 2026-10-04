class Solution {
public:
    int maximumJumps(vector<int>& nums, int target) {
        int n = nums.size();
        vector<int> f(n, -(1 << 30));
        f[n - 1] = 0;
        for (int i = n - 2; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) {
                if (abs(nums[i] - nums[j]) <= target) {
                    f[i] = max(f[i], 1 + f[j]);
                }
            }
        }
        return f[0] < 0 ? -1 : f[0];
    }
};
