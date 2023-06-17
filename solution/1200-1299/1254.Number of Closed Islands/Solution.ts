function closedIsland(grid: number[][]): number {
    const m = grid.length;
    const n = grid[0].length;
    const dirs = [-1, 0, 1, 0, -1];
    const dfs = (i: number, j: number): number => {
        let res = i > 0 && j > 0 && i < m - 1 && j < n - 1 ? 1 : 0;
        grid[i][j] = 1;
        for (let k = 0; k < 4; ++k) {
            const [x, y] = [i + dirs[k], j + dirs[k + 1]];
            if (x >= 0 && y >= 0 && x < m && y < n && grid[x][y] === 0) {
                res &= dfs(x, y);
            }
        }
        return res;
    };
    let ans = 0;
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; j++) {
            if (grid[i][j] === 0) {
                ans += dfs(i, j);
            }
        }
    }
    return ans;
}
