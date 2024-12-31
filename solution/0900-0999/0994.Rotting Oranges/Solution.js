/**
 * @param {number[][]} grid
 * @return {number}
 */
var orangesRotting = function (grid) {
    const m = grid.length;
    const n = grid[0].length;
    let q = [];
    let cnt = 0;
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            if (grid[i][j] === 1) {
                cnt++;
            } else if (grid[i][j] === 2) {
                q.push([i, j]);
            }
        }
    }

    const dirs = [-1, 0, 1, 0, -1];
    for (let ans = 1; q.length && cnt; ++ans) {
        let t = [];
        for (const [i, j] of q) {
            for (let d = 0; d < 4; ++d) {
                const x = i + dirs[d];
                const y = j + dirs[d + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] === 1) {
                    grid[x][y] = 2;
                    t.push([x, y]);
                    if (--cnt === 0) {
                        return ans;
                    }
                }
            }
        }
        q = [...t];
    }

    return cnt > 0 ? -1 : 0;
};
