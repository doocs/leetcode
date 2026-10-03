class Solution {
public:
    long long maxScore(vector<int>& a, vector<int>& b) {
        int m = a.size(), n = b.size();
        vector<vector<long long>> f(m + 1, vector<long long>(n + 1));
        for (int i = 0; i < m; ++i) {
            f[i][n] = LLONG_MIN / 2;
        }
        for (int j = n - 1; j >= 0; --j) {
            for (int i = m - 1; i >= 0; --i) {
                f[i][j] = max(f[i][j + 1], 1LL * a[i] * b[j] + f[i + 1][j + 1]);
            }
        }
        return f[0][0];
    }
};
