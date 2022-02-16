function luckyNumbers(matrix: number[][]): number[] {
    const m = matrix.length;
    const n = matrix[0].length;
    const col = new Array(n).fill(0);
    const res = [];
    for (let j = 0; j < n; j++) {
        for (let i = 0; i < m; i++) {
            col[j] = Math.max(col[j], matrix[i][j]);
        }
    }
    for (let x = 0; x < m; x++) {
        let i = 0;
        for (let y = 1; y < n; y++) {
            if (matrix[x][i] > matrix[x][y]) {
                i = y;
            }
        }
        if (matrix[x][i] === col[i]) {
            res.push(col[i]);
        }
    }
    return res;
}
