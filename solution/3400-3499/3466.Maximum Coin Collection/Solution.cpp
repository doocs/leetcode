class Solution {
public:
    long long maxCoins(vector<int>& lane1, vector<int>& lane2) {
        int n = lane1.size();
        long long ans = -1e18;
        vector<vector<vector<long long>>> f(n, vector<vector<long long>>(2, vector<long long>(3, -1e18)));
        auto dfs = [&](this auto&& dfs, int i, int j, int k) -> long long {
            if (i >= n) {
                return 0LL;
            }
            if (f[i][j][k] != -1e18) {
                return f[i][j][k];
            }
            int x = j == 0 ? lane1[i] : lane2[i];
            long long ans = max((long long) x, dfs(i + 1, j, k) + x);
            if (k > 0) {
                ans = max(ans, dfs(i + 1, j ^ 1, k - 1) + x);
                ans = max(ans, dfs(i, j ^ 1, k - 1));
            }
            return f[i][j][k] = ans;
        };
        for (int i = 0; i < n; ++i) {
            ans = max(ans, dfs(i, 0, 2));
        }
        return ans;
    }
};
