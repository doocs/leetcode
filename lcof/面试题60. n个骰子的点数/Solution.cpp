class Solution {
public:
    vector<double> dicesProbability(int n) {
        int f[n + 1][6 * n + 1];
        memset(f, 0, sizeof f);
        for (int j = 1; j <= 6; ++j) {
            f[1][j] = 1;
        }
        for (int i = 2; i <= n; ++i) {
            for (int j = i; j <= 6 * i; ++j) {
                for (int k = 1; k <= 6; ++k) {
                    if (j >= k) {
                        f[i][j] += f[i - 1][j - k];
                    }
                }
            }
        }
        vector<double> ans;
        double m = pow(6, n);
        for (int j = n; j <= 6 * n; ++j) {
            ans.push_back(f[n][j] / m);
        }
        return ans;
    }
};