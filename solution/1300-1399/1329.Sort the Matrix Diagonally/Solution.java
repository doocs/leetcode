class Solution {
    public int[][] diagonalSort(int[][] mat) {
        for (int i = 0; i < mat.length; i++) {
            handler(mat, i, 0);
        }
        for (int i = 0; i < mat[0].length; i++) {
            handler(mat, 0, i);
        }
        return mat;
    }

    public void handler(int[][] mat, int i, int j) {
        for (; i < mat.length && j < mat[0].length; i++, j++) {
            for (int k = i + 1, p = j + 1; k < mat.length && p < mat[0].length; k++, p++) {
                if (mat[k][p] < mat[i][j]){
                    int temp = mat[k][p];
                    mat[k][p] = mat[i][j];
                    mat[i][j] = temp;
                }
            }
        }
    }
}