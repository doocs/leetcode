/**
 * @param {number} numRows
 * @return {number[][]}
 */
var generate = function (numRows) {
    const f = [[1]];
    for (let i = 0; i < numRows - 1; ++i) {
        const g = [1];
        for (let j = 1; j < f[i].length; ++j) {
            g.push(f[i][j - 1] + f[i][j]);
        }
        g.push(1);
        f.push(g);
    }
    return f;
};
