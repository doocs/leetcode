class Solution {
    public int[] getMaxMatrix(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][] s = new int[m + 1][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                s[i + 1][j] = s[i][j] + matrix[i][j];
            }
        }
        int mx = matrix[0][0];
        int[] ans = new int[] {0, 0, 0, 0};
        for (int i1 = 0; i1 < m; ++i1) {
            for (int i2 = i1; i2 < m; ++i2) {
                int[] nums = new int[n];
                for (int j = 0; j < n; ++j) {
                    nums[j] = s[i2 + 1][j] - s[i1][j];
                }
                int start = 0;
                int f = nums[0];
                for (int j = 1; j < n; ++j) {
                    if (f > 0) {
                        f += nums[j];
                    } else {
                        f = nums[j];
                        start = j;
                    }
                    if (f > mx) {
                        mx = f;
                        ans = new int[] {i1, start, i2, j};
                    }
                }
            }
        }
        return ans;
    }
}