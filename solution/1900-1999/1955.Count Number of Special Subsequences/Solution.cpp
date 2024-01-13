class Solution {
public:
    int countSpecialSubsequences(vector<int>& nums) {
        const int mod = 1e9 + 7;
        int n = nums.size();
        int f[n][3];
        memset(f, 0, sizeof(f));
        f[0][0] = nums[0] == 0;
        for (int i = 1; i < n; ++i) {
            if (nums[i] == 0) {
                f[i][0] = (2 * f[i - 1][0] % mod + 1) % mod;
                f[i][1] = f[i - 1][1];
                f[i][2] = f[i - 1][2];
            } else if (nums[i] == 1) {
                f[i][0] = f[i - 1][0];
                f[i][1] = (f[i - 1][0] + 2 * f[i - 1][1] % mod) % mod;
                f[i][2] = f[i - 1][2];
            } else {
                f[i][0] = f[i - 1][0];
                f[i][1] = f[i - 1][1];
                f[i][2] = (f[i - 1][1] + 2 * f[i - 1][2] % mod) % mod;
            }
        }
        return f[n - 1][2];
    }
};