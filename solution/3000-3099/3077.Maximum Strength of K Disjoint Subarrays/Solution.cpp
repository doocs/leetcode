class Solution {
public:
    long long maximumStrength(vector<int>& nums, int k) {
        int n = nums.size();
        long long f[n + 1][k + 1][2];
        memset(f, -0x3f3f3f3f3f3f3f3f, sizeof(f));
        f[0][0][0] = 0;
        for (int i = 1; i <= n; i++) {
            int x = nums[i - 1];
            for (int j = 0; j <= k; j++) {
                long long sign = (j & 1) == 1 ? 1 : -1;
                long long val = sign * x * (k - j + 1);
                f[i][j][0] = max(f[i - 1][j][0], f[i - 1][j][1]);
                f[i][j][1] = max(f[i][j][1], f[i - 1][j][1] + val);
                if (j > 0) {
                    long long t = max(f[i - 1][j - 1][0], f[i - 1][j - 1][1]) + val;
                    f[i][j][1] = max(f[i][j][1], t);
                }
            }
        }
        return max(f[n][k][0], f[n][k][1]);
    }
};