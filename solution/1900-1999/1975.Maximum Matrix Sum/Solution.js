/**
 * @param {number[][]} matrix
 * @return {number}
 */
var maxMatrixSum = function (matrix) {
    let cnt = 0;
    let s = 0;
    let mi = Infinity;
    for (const row of matrix) {
        for (const v of row) {
            s += Math.abs(v);
            mi = Math.min(mi, Math.abs(v));
            cnt += v < 0;
        }
    }
    if (cnt % 2 == 0) {
        return s;
    }
    return s - mi * 2;
};
