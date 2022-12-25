class Solution {
public:
    const int mod = 1e9 + 7;

    int countPartitions(vector<int>& nums, int k) {
        long s = accumulate(nums.begin(), nums.end(), 0l);
        if (s < k * 2) return 0;
        int n = nums.size();
        long f[n + 1][k];
        int ans = 1;
        memset(f, 0, sizeof f);
        f[0][0] = 1;
        for (int i = 1; i <= n; ++i) {
            int v = nums[i - 1];
            ans = ans * 2 % mod;
            for (int j = 0; j < k; ++j) {
                f[i][j] = f[i - 1][j];
                if (j >= v) {
                    f[i][j] = (f[i][j] + f[i - 1][j - v]) % mod;
                }
            }
        }
        for (int j = 0; j < k; ++j) {
            ans = (ans - f[n][j] * 2 % mod + mod) % mod;
        }
        return ans;
    }
};