function isToeplitzMatrix(matrix: number[][]): boolean {
    const [m, n] = [matrix.length, matrix[0].length];
    for (let i = 1; i < m; ++i) {
        for (let j = 1; j < n; ++j) {
            if (matrix[i][j] !== matrix[i - 1][j - 1]) {
                return false;
            }
        }
    }
    return true;
}
