class Solution {
public:
    int maxScore(int n, int k, vector<vector<int>>& stayScore, vector<vector<int>>& travelScore) {
        int f[k + 1][n];
        memset(f, 0xc0, sizeof(f));
        memset(f[0], 0, sizeof(f[0]));
        for (int i = 1; i <= k; ++i) {
            for (int j = 0; j < n; ++j) {
                for (int h = 0; h < n; ++h) {
                    f[i][j] = max(f[i][j], f[i - 1][h] + (j == h ? stayScore[i - 1][j] : travelScore[h][j]));
                }
            }
        }
        return *max_element(f[k], f[k] + n);
    }
};
