class Solution {
public:
    vector<vector<int>> generateMatrix(int n) {
        vector<vector<int>> res(n, vector<int>(n, 0));
        int num = 1;
        int m1 = 0, m2 = n - 1;
        while (m1 < m2) {
            for (int j = m1; j < m2; ++j) {
                res[m1][j] = num++;
            }
            for (int i = m1; i < m2; ++i) {
                res[i][m2] = num++;
            }
            for (int j = m2; j > m1; --j) {
                res[m2][j] = num++;
            }
            for (int i = m2; i > m1; --i) {
                res[i][m1] = num++;
            }
            ++m1;
            --m2;
        }
        if (m1 == m2) {
            res[m1][m1] = num;
        }
        return res;
    }
};