class Solution {
public:
    int mctFromLeafValues(vector<int>& arr) {
        int n = arr.size();
        int f[n][n];
        int g[n][n];
        memset(f, 0, sizeof(f));
        for (int i = n - 1; ~i; --i) {
            g[i][i] = arr[i];
            for (int j = i + 1; j < n; ++j) {
                g[i][j] = max(g[i][j - 1], arr[j]);
                f[i][j] = 1 << 30;
                for (int k = i; k < j; ++k) {
                    f[i][j] = min(f[i][j], f[i][k] + f[k + 1][j] + g[i][k] * g[k + 1][j]);
                }
            }
        }
        return f[0][n - 1];
    }
};