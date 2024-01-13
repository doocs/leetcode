class Solution {
    public int[] spiralOrder(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return new int[] {};
        }
        int m = matrix.length, n = matrix[0].length;
        int top = 0, bottom = m - 1, left = 0, right = n - 1;
        int[] ans = new int[m * n];
        int k = 0;
        while (left <= right && top <= bottom) {
            for (int j = left; j <= right; ++j) {
                ans[k++] = matrix[top][j];
            }
            for (int i = top + 1; i <= bottom; ++i) {
                ans[k++] = matrix[i][right];
            }
            if (left < right && top < bottom) {
                for (int j = right - 1; j >= left; --j) {
                    ans[k++] = matrix[bottom][j];
                }
                for (int i = bottom - 1; i > top; --i) {
                    ans[k++] = matrix[i][left];
                }
            }
            ++top;
            --bottom;
            ++left;
            --right;
        }
        return ans;
    }
}