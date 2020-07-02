class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        int len = matrix.length;
        int min = matrix[0][0], max = matrix[len - 1][len - 1];
        while (min < max) {
            int mid = min + ((max - min) >> 1);
            if (check(matrix, mid, k, len)) {
                max = mid;
            } else {
                min = mid + 1;
            }
        }

        return min;
    }

    private boolean check(int[][] matrix, int mid, int k, int n) {
        // 从左下角走起
        int i = n - 1;
        int j = 0;
        int num = 0;

        while (i >= 0 && j < n) {
            if (matrix[i][j] <= mid) {
                num += i + 1;
                j++;
            } else {
                i--;
            }
        }

        return num >= k;
    }
}