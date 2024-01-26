class Solution {
public:
    int maximumProcessableQueries(vector<int>& nums, vector<int>& queries) {
        int n = nums.size();
        int f[n][n];
        memset(f, 0, sizeof(f));
        int m = queries.size();
        for (int i = 0; i < n; ++i) {
            for (int j = n - 1; j >= i; --j) {
                if (i > 0) {
                    f[i][j] = max(f[i][j], f[i - 1][j] + (nums[i - 1] >= queries[f[i - 1][j]] ? 1 : 0));
                }
                if (j + 1 < n) {
                    f[i][j] = max(f[i][j], f[i][j + 1] + (nums[j + 1] >= queries[f[i][j + 1]] ? 1 : 0));
                }
                if (f[i][j] == m) {
                    return m;
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans = max(ans, f[i][i] + (nums[i] >= queries[f[i][i]] ? 1 : 0));
        }
        return ans;
    }
};