class Solution {
public:
    int maxScore(vector<int>& nums) {
        int m = nums.size();
        int f[m][m];
        for (int i = 0; i < m; ++i) {
            for (int j = i + 1; j < m; ++j) {
                f[i][j] = gcd(nums[i], nums[j]);
            }
        }
        int dp[1 << m];
        memset(dp, 0, sizeof dp);
        for (int k = 0; k < 1 << m; ++k) {
            int cnt = __builtin_popcount(k);
            if (cnt % 2 == 0) {
                for (int i = 0; i < m; ++i) {
                    if (k >> i & 1) {
                        for (int j = i + 1; j < m; ++j) {
                            if (k >> j & 1) {
                                dp[k] = max(dp[k], dp[k ^ (1 << i) ^ (1 << j)] + cnt / 2 * f[i][j]);
                            }
                        }
                    }
                }
            }
        }
        return dp[(1 << m) - 1];
    }
};