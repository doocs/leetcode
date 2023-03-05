class Solution {
    public int diagonalSum(int[][] mat) {
        int ans = 0;
        int n = mat.length;
        for (int i = 0; i < n; ++i) {
            int j = n - i - 1;
            ans += mat[i][i] + (i == j ? 0 : mat[i][j]);
        }
        return ans;
    }
}