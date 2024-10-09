/**
 * @param {number[][]} matrix
 * @return {number[]}
 */
var luckyNumbers = function (matrix) {
    const m = matrix.length;
    const n = matrix[0].length;
    const rows = new Array(m).fill(1 << 30);
    const cols = new Array(n).fill(0);
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; j++) {
            rows[i] = Math.min(rows[i], matrix[i][j]);
            cols[j] = Math.max(cols[j], matrix[i][j]);
        }
    }
    const ans = [];
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; j++) {
            if (rows[i] === cols[j]) {
                ans.push(rows[i]);
            }
        }
    }
    return ans;
};
