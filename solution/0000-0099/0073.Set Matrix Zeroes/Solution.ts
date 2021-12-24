/**
 Do not return anything, modify matrix in-place instead.
 */
function setZeroes(matrix: number[][]): void {
    let m = matrix.length,
        n = matrix[0].length;
    let c0 = false,
        r0 = false;
    // 遍历第一行
    for (let i = 0; i < m; i++) {
        if (!matrix[i][0] && !c0) {
            c0 = true;
        }
    }
    // 第一列
    for (let j = 0; j < n; j++) {
        if (!matrix[0][j] && !r0) {
            r0 = true;
        }
    }
    // 用第一行、第一列标记全部
    for (let i = 1; i < m; i++) {
        for (let j = 1; j < n; j++) {
            if (!matrix[i][j]) {
                matrix[i][0] = 0;
                matrix[0][j] = 0;
            }
        }
    }
    // set
    for (let i = 1; i < m; i++) {
        for (let j = 1; j < n; j++) {
            if (!matrix[i][0] || !matrix[0][j]) {
                matrix[i][j] = 0;
            }
        }
    }
    // set 第一列
    if (c0) {
        for (let i = 0; i < m; i++) {
            matrix[i][0] = 0;
        }
    }
    // 第一行
    if (r0) {
        for (let j = 0; j < n; j++) {
            matrix[0][j] = 0;
        }
    }
}
