class Solution {
    public void setZeroes(int[][] matrix) {
        int matrixRow = matrix.length, matrixCol = matrix[0].length;
        boolean[] row = new boolean[matrixRow], col = new boolean[matrixCol];
        for (int i = 0; i < matrixRow; i++) {
            for (int j = 0; j < matrixCol; j++) {
                if (matrix[i][j] == 0) {
                    row[i] = true;
                    col[j] = true;
                }
            }
        }
        for (int i = 0; i < matrixRow; i++) {
            if (row[i]) for (int k = 0; k < matrixCol; k++) matrix[i][k] = 0;
        }
        for (int j = 0; j < matrixCol; j++) {
            if (col[j]) for (int k = 0; k < matrixRow; k++) matrix[k][j] = 0;
        }
    }
}