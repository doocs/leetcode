class Solution {
public:
    double champagneTower(int poured, int query_row, int query_glass) {
        double f[101] = {(double) poured};
        double g[101];
        for (int i = 1; i <= query_row; ++i) {
            memset(g, 0, sizeof g);
            for (int j = 0; j < i; ++j) {
                if (f[j] > 1) {
                    double half = (f[j] - 1) / 2.0;
                    g[j] += half;
                    g[j + 1] += half;
                }
            }
            memcpy(f, g, sizeof g);
        }
        return min(1.0, f[query_glass]);
    }
};