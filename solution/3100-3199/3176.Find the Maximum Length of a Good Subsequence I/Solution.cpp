class Solution {
public:
    int maximumLength(vector<int>& nums, int k) {
        int n = nums.size();
        int f[n][k + 1];
        memset(f, 0, sizeof(f));
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            for (int h = 0; h <= k; ++h) {
                for (int j = 0; j < i; ++j) {
                    if (nums[i] == nums[j]) {
                        f[i][h] = max(f[i][h], f[j][h]);
                    } else if (h) {
                        f[i][h] = max(f[i][h], f[j][h - 1]);
                    }
                }
                ++f[i][h];
            }
            ans = max(ans, f[i][k]);
        }
        return ans;
    }
};