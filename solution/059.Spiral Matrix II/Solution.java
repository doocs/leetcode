class Solution {
    public int[][] generateMatrix(int n) {
        if (n < 1) {
            return null;
        }
        
        int[][] res = new int[n][n];
        int val = 1;
        
        int m1 = 0;
        int m2 = n - 1;
        while (m1 < m2) {
            for (int j = m1; j < m2; ++j) {
                res[m1][j] = val++;
            }
            for (int i = m1; i < m2; ++i) {
                res[i][m2] = val++;
            }
            for (int j = m2; j > m1; --j) {
                res[m2][j] = val++;
            }
            for (int i = m2; i > m1; --i) {
                res[i][m1] = val++;
            }
            ++m1;
            --m2;
        }
        if (m1 == m2) {
            res[m1][m1] = val;
        }
        
        return res;
    }
}