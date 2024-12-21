class Solution {
public:
    double largestSumOfAverages(vector<int>& nums, int k) {
        int n = nums.size();
        int s[n + 1];
        double f[n][k + 1];
        memset(f, 0, sizeof(f));
        s[0] = 0;
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + nums[i];
        }
        auto dfs = [&](this auto&& dfs, int i, int k) -> double {
            if (i == n) {
                return 0;
            }
            if (k == 1) {
                return (s[n] - s[i]) * 1.0 / (n - i);
            }
            if (f[i][k] > 0) {
                return f[i][k];
            }
            double ans = 0;
            for (int j = i + 1; j < n; ++j) {
                ans = max(ans, (s[j] - s[i]) * 1.0 / (j - i) + dfs(j, k - 1));
            }
            return f[i][k] = ans;
        };
        return dfs(0, k);
    }
};
