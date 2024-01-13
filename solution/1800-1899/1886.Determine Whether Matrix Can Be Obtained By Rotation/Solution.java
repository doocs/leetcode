class Solution {
    public boolean findRotation(int[][] mat, int[][] target) {
        int times = 4;
        while (times-- > 0) {
            if (equals(mat, target)) {
                return true;
            }
            rotate(mat);
        }
        return false;
    }

    private void rotate(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n / 2; ++i) {
            for (int j = i; j < n - 1 - i; ++j) {
                int t = matrix[i][j];
                matrix[i][j] = matrix[n - j - 1][i];
                matrix[n - j - 1][i] = matrix[n - i - 1][n - j - 1];
                matrix[n - i - 1][n - j - 1] = matrix[j][n - i - 1];
                matrix[j][n - i - 1] = t;
            }
        }
    }

    private boolean equals(int[][] nums1, int[][] nums2) {
        int n = nums1.length;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (nums1[i][j] != nums2[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}