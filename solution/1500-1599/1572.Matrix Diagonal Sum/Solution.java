class Solution {
    public int diagonalSum(int[][] mat) {
        int n = mat.length;
        int res = 0;
        for (int i = 0; i < n; ++i) {
            res += mat[i][i] + (n - i - 1 == i ? 0 : mat[i][n - i - 1]);
        }
        return res;
    }
}