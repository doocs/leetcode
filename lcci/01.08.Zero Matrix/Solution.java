class Solution {
    public void setZeroes(int[][] matrix) {
        int rows = matrix.length, cols = matrix[0].length;
        Set<Integer> zeroRows = new HashSet<>();
        Set<Integer> zeroCols = new HashSet<>();
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                if (matrix[i][j] == 0) {
                    zeroRows.add(i);
                    zeroCols.add(j);
                }
            }
        }

        // 行清零
        for (int row : zeroRows) {
            for (int j = 0; j < cols; ++j) {
                matrix[row][j] = 0;
            }
        }

        // 列清零
        for (int col : zeroCols) {
            for (int i = 0; i < rows; ++i) {
                matrix[i][col] = 0;
            }
        }
    }
}