function checkValid(matrix: number[][]): boolean {
    const n = matrix.length;
    let rows = Array.from({ length: n }, () => new Array(n).fill(false));
    let cols = Array.from({ length: n }, () => new Array(n).fill(false));
    for (let i = 0; i < n; i++) {
        for (let j = 0; j < n; j++) {
            let cur = matrix[i][j];
            if (rows[i][cur] || cols[j][cur]) return false;
            rows[i][cur] = true;
            cols[j][cur] = true;
        }
    }
    return true;
}
