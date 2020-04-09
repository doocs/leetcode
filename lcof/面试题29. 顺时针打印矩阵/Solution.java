class Solution {
    private int index;

    public int[] spiralOrder(int[][] matrix) {
        if (matrix.length == 0) {
            return new int[0];
        }
        index = 0;
        int m = matrix.length, n = matrix[0].length;
        int s1 = 0, e1 = 0, s2 = m - 1, e2 = n - 1;
        int[] res = new int[m * n];
        while (s1 <= s2 && e1 <= e2) {
            spiralAdd(matrix, s1++, e1++, s2--, e2--, res);
        }
        return res;
    }

    public void spiralAdd(int[][] matrix, int s1, int e1, int s2, int e2, int[] res) {
        if (s1 == s2) {
            for (int j = e1; j <= e2; ++j) {
                res[index++] = matrix[s1][j];
            }
            return;
        }
        if (e1 == e2) {
            for (int i = s1; i <= s2; ++i) {
                res[index++] = matrix[i][e1];
            }
            return;
        }

        for (int j = e1; j < e2; ++j) {
            res[index++] = matrix[s1][j];
        }
        for (int i = s1; i < s2; ++i) {
            res[index++] = matrix[i][e2];
        }
        for (int j = e2; j > e1; --j) {
            res[index++] = matrix[s2][j];
        }
        for (int i = s2; i > s1; --i) {
            res[index++] = matrix[i][e1];
        }
    }
}