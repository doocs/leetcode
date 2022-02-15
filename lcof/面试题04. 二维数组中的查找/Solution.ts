function findNumberIn2DArray(matrix: number[][], target: number): boolean {
    if (matrix.length == 0 || matrix[0].length == 0) {
        return false;
    }
    const m = matrix.length;
    const n = matrix[0].length;
    for (let i = 0, j = n - 1; i < m && j >= 0; ) {
        if (matrix[i][j] == target) {
            return true;
        }
        if (matrix[i][j] < target) {
            ++i;
        } else {
            --j;
        }
    }
    return false;
}
