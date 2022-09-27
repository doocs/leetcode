class Solution {
    public double champagneTower(int poured, int query_row, int query_glass) {
        double[][] g = new double[110][110];
        g[0][0] = poured;
        for (int i = 0; i <= query_row; ++i) {
            for (int j = 0; j <= i; ++j) {
                if (g[i][j] > 1) {
                    double half = (g[i][j] - 1) / 2.0;
                    g[i][j] = 1;
                    g[i + 1][j] += half;
                    g[i + 1][j + 1] += half;
                }
            }
        }
        return g[query_row][query_glass];
    }
}