/**
 * @param {number[][]} grid
 * @return {number[][]}
 */
var sortMatrix = function (grid) {
    const n = grid.length;
    for (let k = n - 2; k >= 0; --k) {
        let i = k,
            j = 0;
        const t = [];
        while (i < n && j < n) {
            t.push(grid[i++][j++]);
        }
        t.sort((a, b) => a - b);
        for (const x of t) {
            grid[--i][--j] = x;
        }
    }
    for (let k = n - 2; k > 0; --k) {
        let i = k,
            j = n - 1;
        const t = [];
        while (i >= 0 && j >= 0) {
            t.push(grid[i--][j--]);
        }
        t.sort((a, b) => a - b);
        for (const x of t) {
            grid[++i][++j] = x;
        }
    }
    return grid;
};
