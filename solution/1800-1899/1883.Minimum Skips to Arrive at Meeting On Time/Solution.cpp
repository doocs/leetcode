class Solution {
public:
    int minSkips(vector<int>& dist, int speed, int hoursBefore) {
        int n = dist.size();
        vector<vector<double>> f(n + 1, vector<double>(n + 1, 1e20));
        f[0][0] = 0;
        double eps = 1e-8;
        for (int i = 1; i <= n; ++i) {
            for (int j = 0; j <= i; ++j) {
                if (j < i) {
                    f[i][j] = min(f[i][j], ceil(f[i - 1][j] + dist[i - 1] * 1.0 / speed - eps));
                }
                if (j) {
                    f[i][j] = min(f[i][j], f[i - 1][j - 1] + dist[i - 1] * 1.0 / speed);
                }
            }
        }
        for (int j = 0; j <= n; ++j) {
            if (f[n][j] <= hoursBefore + eps) {
                return j;
            }
        }
        return -1;
    }
};