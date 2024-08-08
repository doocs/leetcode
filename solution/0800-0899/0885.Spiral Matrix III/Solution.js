/**
 * @param {number} rows
 * @param {number} cols
 * @param {number} rStart
 * @param {number} cStart
 * @return {number[][]}
 */
var spiralMatrixIII = function (rows, cols, rStart, cStart) {
    let result = [];
    let totalCells = rows * cols;
    let directions = [
        [0, 1],
        [1, 0],
        [0, -1],
        [-1, 0],
    ];
    let step = 0;
    let d = 0;
    let r = rStart,
        c = cStart;

    result.push([r, c]);

    while (result.length < totalCells) {
        if (d === 0 || d === 2) step++;

        for (let i = 0; i < step; i++) {
            r += directions[d][0];
            c += directions[d][1];

            if (r >= 0 && r < rows && c >= 0 && c < cols) {
                result.push([r, c]);
            }
        }

        d = (d + 1) % 4;
    }

    return result;
};
