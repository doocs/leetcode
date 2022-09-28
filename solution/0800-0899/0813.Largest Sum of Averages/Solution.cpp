class Solution {
public:
    double f[110][110];
    int s[110];
    int n;

    double largestSumOfAverages(vector<int>& nums, int k) {
        n = nums.size();
        s[0] = 0;
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + nums[i];
        }
        memset(f, -1, sizeof f);
        return dfs(0, k);
    }

    double dfs(int i, int k) {
        if (i == n) {
            return 0;
        }
        if (k == 1) {
            return (s[n] - s[i]) * 1.0 / (n - i);
        }
        if (f[i][k] >= 0) {
            return f[i][k];
        }
        double ans = 0;
        for (int j = i; j < n; ++j) {
            double t = (s[j + 1] - s[i]) * 1.0 / (j - i + 1) + dfs(j + 1, k - 1);
            ans = max(ans, t); 
        }
        f[i][k] = ans;
        return ans;
    }
};