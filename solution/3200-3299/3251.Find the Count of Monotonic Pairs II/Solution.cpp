class Solution {
public:
    int countOfPairs(vector<int>& nums) {
        const int mod = 1e9 + 7;
        int n = nums.size();
        int m = *max_element(nums.begin(), nums.end());
        vector<vector<int>> f(n, vector<int>(m + 1));
        for (int j = 0; j <= nums[0]; ++j) {
            f[0][j] = 1;
        }
        vector<int> g(m + 1);
        for (int i = 1; i < n; ++i) {
            g[0] = f[i - 1][0];
            for (int j = 1; j <= m; ++j) {
                g[j] = (g[j - 1] + f[i - 1][j]) % mod;
            }
            for (int j = 0; j <= nums[i]; ++j) {
                int k = min(j, j + nums[i - 1] - nums[i]);
                if (k >= 0) {
                    f[i][j] = g[k];
                }
            }
        }
        int ans = 0;
        for (int j = 0; j <= nums[n - 1]; ++j) {
            ans = (ans + f[n - 1][j]) % mod;
        }
        return ans;
    }
};
