class Solution {
public:
    int maxVacationDays(vector<vector<int>>& flights, vector<vector<int>>& days) {
        int n = flights.size();
        int K = days[0].size();
        int f[K + 1][n];
        memset(f, -0x3f, sizeof(f));
        f[0][0] = 0;
        for (int k = 1; k <= K; ++k) {
            for (int j = 0; j < n; ++j) {
                f[k][j] = f[k - 1][j];
                for (int i = 0; i < n; ++i) {
                    if (flights[i][j] == 1) {
                        f[k][j] = max(f[k][j], f[k - 1][i]);
                    }
                }
                f[k][j] += days[j][k - 1];
            }
        }
        int ans = 0;
        for (int j = 0; j < n; ++j) {
            ans = max(ans, f[K][j]);
        }
        return ans;
    }
};