class Solution {
public:
    double knightProbability(int n, int k, int row, int column) {
        double f[k + 1][n][n];
        memset(f, 0, sizeof(f));
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                f[0][i][j] = 1;
            }
        }
        int dirs[9] = {-2, -1, 2, 1, -2, 1, 2, -1, -2};
        for (int h = 1; h <= k; ++h) {
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; ++j) {
                    for (int p = 0; p < 8; ++p) {
                        int x = i + dirs[p], y = j + dirs[p + 1];
                        if (x >= 0 && x < n && y >= 0 && y < n) {
                            f[h][i][j] += f[h - 1][x][y] / 8;
                        }
                    }
                }
            }
        }
        return f[k][row][column];
    }
};