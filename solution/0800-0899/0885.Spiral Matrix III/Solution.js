/**
 * @param {number} rows
 * @param {number} cols
 * @param {number} rStart
 * @param {number} cStart
 * @return {number[][]}
 */
var spiralMatrixIII = function (rows, cols, rStart, cStart) {
    // prettier-ignore
    const dir = [[1,0],[0,1],[-1,0],[0,-1]]
    let [x, y, i, size] = [cStart, rStart, 0, 0];
    const ans = [[y, x]];
    const total = rows * cols;

    while (ans.length < total) {
        if (i % 2 === 0) size++;

        for (let j = 0; ans.length < total && j < size; j++) {
            x += dir[i][0];
            y += dir[i][1];

            if (0 <= x && x < cols && 0 <= y && y < rows) {
                ans.push([y, x]);
            }
        }

        i = (i + 1) % 4;
    }

    return ans;
};
