/**
 * @param {number[][]} grid
 * @return {number}
 */
var getMaximumGold = function (grid) {
    const m = grid.length;
    const n = grid[0].length;
    const dfs = (i, j) => {
        if (i < 0 || i >= m || j < 0 || j >= n || !grid[i][j]) {
            return 0;
        }
        const v = grid[i][j];
        grid[i][j] = 0;
        let ans = v + Math.max(dfs(i - 1, j), dfs(i + 1, j), dfs(i, j - 1), dfs(i, j + 1));
        grid[i][j] = v;
        return ans;
    };
    let ans = 0;
    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            ans = Math.max(ans, dfs(i, j));
        }
    }
    return ans;
};
