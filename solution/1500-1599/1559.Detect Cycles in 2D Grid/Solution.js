/**
 * @param {character[][]} grid
 * @return {boolean}
 */
var containsCycle = function (grid) {
    const m = grid.length;
    const n = grid[0].length;
    let p = Array.from({ length: m * n }, (_, i) => i);
    function find(x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }
    const dirs = [0, 1, 0];
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            for (let k = 0; k < 2; ++k) {
                const x = i + dirs[k];
                const y = j + dirs[k + 1];
                if (x < m && y < n && grid[x][y] == grid[i][j]) {
                    if (find(x * n + y) == find(i * n + j)) {
                        return true;
                    }
                    p[find(x * n + y)] = find(i * n + j);
                }
            }
        }
    }
    return false;
};
