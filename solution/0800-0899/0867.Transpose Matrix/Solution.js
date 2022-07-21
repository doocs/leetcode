/**
 * @param {number[][]} matrix
 * @return {number[][]}
 */
var transpose = function (matrix) {
    const m = matrix.length;
    const n = matrix[0].length;
    const ans = new Array(n).fill(0).map(() => new Array(m).fill(0));
    for (let i = 0; i < n; ++i) {
        for (let j = 0; j < m; ++j) {
            ans[i][j] = matrix[j][i];
        }
    }
    return ans;
};
