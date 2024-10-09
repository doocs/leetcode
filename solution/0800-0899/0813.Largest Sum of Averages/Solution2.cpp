class Solution {
public:
    double largestSumOfAverages(vector<int>& nums, int k) {
        int n = nums.size();
        int s[n + 1];
        s[0] = 0;
        double f[n + 1][k + 1];
        memset(f, 0, sizeof(f));
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + nums[i];
        }
        for (int i = 1; i <= n; ++i) {
            f[i][1] = s[i] * 1.0 / i;
            for (int j = 2; j <= min(i, k); ++j) {
                for (int h = 0; h < i; ++h) {
                    f[i][j] = max(f[i][j], f[h][j - 1] + (s[i] - s[h]) * 1.0 / (i - h));
                }
            }
        }
        return f[n][k];
    }
};
