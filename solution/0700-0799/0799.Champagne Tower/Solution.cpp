class Solution {
public:
    double champagneTower(int poured, int query_row, int query_glass) {
        double f[101][101] = {0.0};
        f[0][0] = poured;
        for (int i = 0; i <= query_row; ++i) {
            for (int j = 0; j <= i; ++j) {
                if (f[i][j] > 1) {
                    double half = (f[i][j] - 1) / 2.0;
                    f[i][j] = 1;
                    f[i + 1][j] += half;
                    f[i + 1][j + 1] += half;
                }
            }
        }
        return f[query_row][query_glass];
    }
};