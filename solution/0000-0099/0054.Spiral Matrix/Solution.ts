function spiralOrder(matrix: number[][]): number[] {
    const m = matrix.length;
    const n = matrix[0].length;
    const res = [];
    for (let i = 0; i <= m >> 1; i++) {
        for (let j = i; j < n - i - 1; j++) {
            res.push(matrix[i][j]);
        }
        for (let j = i; j < m - i - 1; j++) {
            res.push(matrix[j][n - i - 1]);
        }
        for (let j = i; j < n - i - 1; j++) {
            res.push(matrix[m - i - 1][n - j - 1]);
        }
        for (let j = i; j < m - i - 1; j++) {
            res.push(matrix[m - j - 1][i]);
        }
    }
    if (m & 1) {
        res.push(matrix[m >> 1][n >> 1]);
    }
    return res.slice(0, m * n);
}
