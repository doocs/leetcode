class Solution {
public:
    double largestSumOfAverages(vector<int>& nums, int k) {
        int n = nums.size();
        int s[n + 1];
        double f[n][k + 1];
        s[0] = 0;
        memset(f, 0, sizeof f);
        for (int i = 0; i < n; ++i) s[i + 1] = s[i] + nums[i];
        function<double(int, int)> dfs = [&](int i, int k) -> double {
            if (i == n) return 0;
            if (k == 1) return (s[n] - s[i]) * 1.0 / (n - i);
            if (f[i][k]) return f[i][k];
            double ans = 0;
            for (int j = i; j < n; ++j) {
                double t = (s[j + 1] - s[i]) * 1.0 / (j - i + 1) + dfs(j + 1, k - 1);
                ans = max(ans, t);
            }
            return f[i][k] = ans;
        };
        return dfs(0, k);
    }
};