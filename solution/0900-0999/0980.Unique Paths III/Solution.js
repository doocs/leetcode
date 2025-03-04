/**
 * @param {number[][]} grid
 * @return {number}
 */
var uniquePathsIII = function (grid) {
    const m = grid.length;
    const n = grid[0].length;
    let [x, y] = [0, 0];
    let cnt = 0;
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            if (grid[i][j] === 0) {
                ++cnt;
            } else if (grid[i][j] === 1) {
                [x, y] = [i, j];
            }
        }
    }
    const vis = Array.from({ length: m }, () => Array(n).fill(false));
    vis[x][y] = true;
    const dirs = [-1, 0, 1, 0, -1];
    const dfs = function (i, j, k) {
        if (grid[i][j] === 2) {
            return k === cnt + 1 ? 1 : 0;
        }
        let ans = 0;
        for (let d = 0; d < 4; ++d) {
            const x = i + dirs[d];
            const y = j + dirs[d + 1];
            if (x >= 0 && x < m && y >= 0 && y < n && !vis[x][y] && grid[x][y] !== -1) {
                vis[x][y] = true;
                ans += dfs(x, y, k + 1);
                vis[x][y] = false;
            }
        }
        return ans;
    };
    return dfs(x, y, 0);
};
