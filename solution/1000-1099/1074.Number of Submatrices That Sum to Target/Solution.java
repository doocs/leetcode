class Solution {
    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        int row = matrix.length, col = matrix[0].length;
        int[][] sum = new int[row][col];
        int ans = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (i == 0 && j == 0) {
                    sum[i][j] = matrix[i][j];
                } else if (i == 0) {
                    sum[i][j] = matrix[i][j] + sum[i][j - 1];
                } else if (j == 0) {
                    sum[i][j] = matrix[i][j] + sum[i - 1][j];
                } else {
                    sum[i][j] = matrix[i][j] - sum[i - 1][j - 1] + sum[i - 1][j] + sum[i][j - 1];
                }
                for (int k = 0; k <= i; k++) {
                    for (int l = 0; l <= j; l++) {
                        int main = (k != 0 && l != 0) ? sum[k - 1][l - 1] : 0;
                        int left = k != 0 ? sum[k - 1][j] : 0;
                        int up = l != 0 ? sum[i][l - 1] : 0;
                        if (sum[i][j] - left - up + main == target) {
                            ans++;
                        }
                    }
                }
            }
        }
        return ans;
    }
}