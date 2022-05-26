/**
 * @param {number[][]} matrix
 * @return {void} Do not return anything, modify matrix in-place instead.
 */
var setZeroes = function (matrix) {
    let m = matrix.length,
        n = matrix[0].length;
    let rows = new Array(m).fill(false);
    let cols = new Array(n).fill(false);
    // 标记
    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            if (matrix[i][j] == 0) {
                rows[i] = true;
                cols[j] = true;
            }
        }
    }
    // 清零
    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            if (matrix[i][j] != 0 && (rows[i] || cols[j])) {
                matrix[i][j] = 0;
            }
        }
    }
};
