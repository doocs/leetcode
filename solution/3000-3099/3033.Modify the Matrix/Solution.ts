function modifiedMatrix(matrix: number[][]): number[][] {
    const rows = matrix.length;
    const cols = matrix[0].length;
    for (let i = 0; i < cols; i++) {
        let maxVal = Number.MIN_SAFE_INTEGER;
        for (let j = 0; j < rows; j++) {
            maxVal = Math.max(maxVal, matrix[j][i]);
        }
        for (let j = 0; j < rows; j++) {
            if (matrix[j][i] === -1) {
                matrix[j][i] = maxVal;
            }
        }
    }
    return matrix;
}
