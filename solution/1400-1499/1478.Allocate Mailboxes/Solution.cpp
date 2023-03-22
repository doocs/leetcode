class Solution {
public:
    int minDistance(vector<int>& houses, int k) {
        int n = houses.size();
        sort(houses.begin(), houses.end());
        int g[n][n];
        memset(g, 0, sizeof(g));
        for (int i = n - 2; ~i; --i) {
            for (int j = i + 1; j < n; ++j) {
                g[i][j] = g[i + 1][j - 1] + houses[j] - houses[i];
            }
        }
        int f[n][k + 1];
        memset(f, 0x3f, sizeof(f));
        for (int i = 0; i < n; ++i) {
            f[i][1] = g[0][i];
            for (int j = 1; j <= k && j <= i + 1; ++j) {
                for (int p = 0; p < i; ++p) {
                    f[i][j] = min(f[i][j], f[p][j - 1] + g[p + 1][i]);
                }
            }
        }
        return f[n - 1][k];
    }
};