class Solution {
    private int[] res;
    private int index;

    public int[] spiralOrder(int[][] matrix) {
        int m, n;
        if (matrix == null || (m = matrix.length) == 0 || matrix[0] == null || (n = matrix[0].length) == 0)
            return new int[]{};
        res = new int[m * n];
        index = 0;
        int i1 = 0, i2 = m - 1;
        int j1 = 0, j2 = n - 1;
        while (i1 <= i2 && j1 <= j2) {
            add(matrix, i1++, j1++, i2--, j2--);
        }
        return res;
    }

    private void add(int[][] matrix, int i1, int j1, int i2, int j2) {
        if (i1 == i2) {
            for (int j = j1; j <= j2; ++j) {
                res[index++] = matrix[i1][j];
            }
            return;
        }
        if (j1 == j2) {
            for (int i = i1; i <= i2; ++i) {
                res[index++] = matrix[i][j1];
            }
            return;
        }
        for (int j = j1; j < j2; ++j) {
            res[index++] = matrix[i1][j];
        }
        for (int i = i1; i < i2; ++i) {
            res[index++] = matrix[i][j2];
        }
        for (int j = j2; j > j1; --j) {
            res[index++] = matrix[i2][j];
        }
        for (int i = i2; i > i1; --i) {
            res[index++] = matrix[i][j1];
        }
    }
}