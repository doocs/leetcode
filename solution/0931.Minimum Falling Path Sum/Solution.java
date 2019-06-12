class Solution {
    public int minFallingPathSum(int[][] A) {
        int m = A.length, n = A[0].length;
        for (int i = 1; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                int min = A[i - 1][j];
                if (j > 0) min = Math.min(min, A[i - 1][j - 1]);
                if (j < n - 1) min = Math.min(min, A[i - 1][j + 1]);
                A[i][j] += min;
            }
        }
        return Arrays.stream(A[m - 1]).min().getAsInt();
    }
}
