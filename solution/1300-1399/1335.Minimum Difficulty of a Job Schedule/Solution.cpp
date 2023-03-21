class Solution {
public:
    int minDifficulty(vector<int>& jobDifficulty, int d) {
        int n = jobDifficulty.size();
        int f[n + 1][d + 1];
        memset(f, 0x3f, sizeof(f));
        f[0][0] = 0;
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= d; ++j) {
                int mx = 0;
                for (int k = i; k; --k) {
                    mx = max(mx, jobDifficulty[k - 1]);
                    f[i][j] = min(f[i][j], f[k - 1][j - 1] + mx);
                }
            }
        }
        return f[n][d] == 0x3f3f3f3f ? -1 : f[n][d];
    }
};