/**
 * @param {number[][]} matrix
 * @return {number}
 */
var countSquares = function (matrix) {
    const m = matrix.length;
    const n = matrix[0].length;
    const f = Array.from({ length: m }, () => Array(n).fill(0));
    let ans = 0;

    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            if (matrix[i][j] === 0) {
                continue;
            }
            if (i === 0 || j === 0) {
                f[i][j] = 1;
            } else {
                f[i][j] = Math.min(f[i - 1][j - 1], Math.min(f[i - 1][j], f[i][j - 1])) + 1;
            }
            ans += f[i][j];
        }
    }

    return ans;
};
