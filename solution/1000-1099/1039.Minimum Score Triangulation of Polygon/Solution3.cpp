class Solution {
public:
    int minScoreTriangulation(vector<int>& values) {
        int n = values.size();
        int f[n][n];
        memset(f, 0, sizeof(f));
        for (int l = 3; l <= n; ++l) {
            for (int i = 0; i + l - 1 < n; ++i) {
                int j = i + l - 1;
                f[i][j] = 1 << 30;
                for (int k = i + 1; k < j; ++k) {
                    f[i][j] = min(f[i][j], f[i][k] + f[k][j] + values[i] * values[k] * values[j]);
                }
            }
        }
        return f[0][n - 1];
    }
};