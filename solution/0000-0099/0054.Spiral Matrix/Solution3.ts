function spiralOrder(matrix: number[][]): number[] {
    const m = matrix.length;
    const n = matrix[0].length;
    let x1 = 0;
    let y1 = 0;
    let x2 = m - 1;
    let y2 = n - 1;
    const ans: number[] = [];
    while (x1 <= x2 && y1 <= y2) {
        for (let j = y1; j <= y2; ++j) {
            ans.push(matrix[x1][j]);
        }
        for (let i = x1 + 1; i <= x2; ++i) {
            ans.push(matrix[i][y2]);
        }
        if (x1 < x2 && y1 < y2) {
            for (let j = y2 - 1; j >= y1; --j) {
                ans.push(matrix[x2][j]);
            }
            for (let i = x2 - 1; i > x1; --i) {
                ans.push(matrix[i][y1]);
            }
        }
        ++x1;
        ++y1;
        --x2;
        --y2;
    }
    return ans;
}
