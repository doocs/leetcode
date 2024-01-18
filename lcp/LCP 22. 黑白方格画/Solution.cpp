class Solution {
public:
    int paintingPlan(int n, int k) {
        if (k == n * n) {
            return 1;
        }
        int c[n + 1][n + 1];
        memset(c, 0, sizeof(c));
        for (int i = 0; i <= n; ++i) {
            c[i][0] = 1;
            for (int j = 1; j <= i; ++j) {
                c[i][j] = c[i - 1][j - 1] + c[i - 1][j];
            }
        }
        int ans = 0;
        for (int i = 0; i <= n; ++i) {
            for (int j = 0; j <= n; ++j) {
                if (n * (i + j) - i * j == k) {
                    ans += c[n][i] * c[n][j];
                }
            }
        }
        return ans;
    }
};