class Solution {
public:
    double probabilityOfHeads(vector<double>& prob, int target) {
        int n = prob.size();
        double f[n + 1][target + 1];
        memset(f, 0, sizeof(f));
        f[0][0] = 1;
        for (int i = 1; i <= n; ++i) {
            for (int j = 0; j <= min(i, target); ++j) {
                f[i][j] = (1 - prob[i - 1]) * f[i - 1][j];
                if (j > 0) {
                    f[i][j] += prob[i - 1] * f[i - 1][j - 1];
                }
            }
        }
        return f[n][target];
    }
};