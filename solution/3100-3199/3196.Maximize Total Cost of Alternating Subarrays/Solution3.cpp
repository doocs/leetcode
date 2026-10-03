class Solution {
public:
    long long maximumTotalCost(vector<int>& nums) {
        int n = nums.size();
        vector<vector<long long>> f(n + 1, vector<long long>(2));
        for (int i = n - 1; i >= 0; --i) {
            for (int j = 0; j < 2; ++j) {
                f[i][j] = nums[i] + f[i + 1][1];
                if (j) {
                    f[i][j] = max(f[i][j], -nums[i] + f[i + 1][0]);
                }
            }
        }
        return f[0][0];
    }
};
