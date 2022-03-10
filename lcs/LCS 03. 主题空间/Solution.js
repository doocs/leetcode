/**
 * @param {string[]} grid
 * @return {number}
 */
var largestArea = function (grid) {
    const m = grid.length;
    const n = grid[0].length;
    let p = new Array(m * n + 1).fill(0);
    let size = new Array(m * n + 1).fill(1);
    for (let i = 0; i < p.length; ++i) {
        p[i] = i;
    }
    const dirs = [-1, 0, 1, 0, -1];
    function find(x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            if (
                i == 0 ||
                i == m - 1 ||
                j == 0 ||
                j == n - 1 ||
                grid[i][j] == '0'
            ) {
                p[find(i * n + j)] = find(m * n);
            } else {
                for (let k = 0; k < 4; ++k) {
                    const x = i + dirs[k];
                    const y = j + dirs[k + 1];
                    if (
                        (grid[x][y] == '0' || grid[i][j] == grid[x][y]) &&
                        find(x * n + y) != find(i * n + j)
                    ) {
                        size[find(x * n + y)] += size[find(i * n + j)];
                        p[find(i * n + j)] = find(x * n + y);
                    }
                }
            }
        }
    }
    let ans = 0;
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            if (find(i * n + j) != find(m * n) && ans < size[i * n + j]) {
                ans = size[i * n + j];
            }
        }
    }
    return ans;
};
