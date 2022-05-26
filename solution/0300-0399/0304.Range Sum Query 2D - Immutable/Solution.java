public class NumMatrix {

	public long[][] sumMatrix;

	public NumMatrix(int[][] matrix) {

		if (matrix == null || matrix.length == 0) {
			return;
		}

		sumMatrix = new long[matrix.length + 1][matrix[0].length + 1];

		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				sumMatrix[i][j + 1] = sumMatrix[i][j] + matrix[i][j];
			}
		}
	}

	public int sumRegion(int row1, int col1, int row2, int col2) {

		if (sumMatrix == null || row1 < 0 || row2 < 0 || col1 < 0
				|| col2 < 0 || row1 >= sumMatrix.length - 1
				|| row2 >= sumMatrix.length - 1
				|| col1 >= sumMatrix[0].length - 1
				|| col2 >= sumMatrix[0].length - 1 || row1 > row2
				|| col1 > col2) {
			return Integer.MIN_VALUE;
		}

		long rt = 0;

		for (int i = row1; i <= row2; i++) {
			rt += sumMatrix[i][col2 + 1] - sumMatrix[i][col1];
		}

		return (int) rt;
	}

}


// Your NumMatrix object will be instantiated and called as such:
// NumMatrix numMatrix = new NumMatrix(matrix);
// numMatrix.sumRegion(0, 1, 2, 3);
// numMatrix.sumRegion(1, 2, 3, 4);