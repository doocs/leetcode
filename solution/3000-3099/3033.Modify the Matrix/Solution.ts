function modifiedMatrix(matrix: number[][]): number[][] {
    const [m, n] = [matrix.length, matrix[0].length];
    for (let j = 0; j < n; ++j) {
        let mx = -1;
        for (let i = 0; i < m; ++i) {
            mx = Math.max(mx, matrix[i][j]);
        }
        for (let i = 0; i < m; ++i) {
            if (matrix[i][j] === -1) {
                matrix[i][j] = mx;
            }
        }
    }
    return matrix;
}
