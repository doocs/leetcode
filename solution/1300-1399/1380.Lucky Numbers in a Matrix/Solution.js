/**
 * @param {number[][]} matrix
 * @return {number[]}
 */
var luckyNumbers = function (matrix) {
    const [m, n] = [matrix.length, matrix[0].length];
    const mins = Array(n);

    for (let i = 0; i < n; i++) {
        let [max, iMax] = [Number.NEGATIVE_INFINITY, -1];

        for (let j = 0; j < m; j++) {
            if (matrix[j][i] > max) {
                max = matrix[j][i];
                iMax = j;
            }
        }

        mins[iMax] ??= Math.min(...matrix[iMax]);
        if (mins[iMax] === max) return [max];
    }

    return [];
};
