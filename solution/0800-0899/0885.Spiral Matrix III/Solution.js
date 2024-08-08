/**
 * @param {number} rows
 * @param {number} cols
 * @param {number} rStart
 * @param {number} cStart
 * @return {number[][]}
 */
var spiralMatrixIII = function (rows, cols, rStart, cStart) {
    const ans = [];
    const totalCells = rows * cols;
    const directions = [
        [0, 1],
        [1, 0],
        [0, -1],
        [-1, 0],
    ];
    let step = 0;
    let d = 0;
    let [r, c] = [rStart, cStart];
    ans.push([r, c]);
    while (ans.length < totalCells) {
        if (d === 0 || d === 2) {
            step++;
        }
        for (let i = 0; i < step; i++) {
            r += directions[d][0];
            c += directions[d][1];
            if (r >= 0 && r < rows && c >= 0 && c < cols) {
                ans.push([r, c]);
            }
        }
        d = (d + 1) % 4;
    }
    return ans;
};
