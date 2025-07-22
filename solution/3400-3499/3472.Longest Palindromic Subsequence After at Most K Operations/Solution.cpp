class Solution {
public:
    int longestPalindromicSubsequence(string s, int k) {
        int n = s.size();
        vector f(n, vector(n, vector<int>(k + 1, -1)));
        auto dfs = [&](this auto&& dfs, int i, int j, int k) -> int {
            if (i > j) {
                return 0;
            }
            if (i == j) {
                return 1;
            }
            if (f[i][j][k] != -1) {
                return f[i][j][k];
            }
            int res = max(dfs(i + 1, j, k), dfs(i, j - 1, k));
            int d = abs(s[i] - s[j]);
            int t = min(d, 26 - d);
            if (t <= k) {
                res = max(res, 2 + dfs(i + 1, j - 1, k - t));
            }
            return f[i][j][k] = res;
        };
        return dfs(0, n - 1, k);
    }
};
