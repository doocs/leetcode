/**
 * @param {number[][]} matrix
 * @return {number[][]}
 */
var transpose = function (matrix) {
    const [m, n] = [matrix.length, matrix[0].length];
    const ans = Array.from({ length: n }, () => Array(m).fill(0));
    for (let i = 0; i < n; ++i) {
        for (let j = 0; j < m; ++j) {
            ans[i][j] = matrix[j][i];
        }
    }
    return ans;
};
