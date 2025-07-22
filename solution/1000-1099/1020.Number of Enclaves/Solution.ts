function numEnclaves(grid: number[][]): number {
    const [m, n] = [grid.length, grid[0].length];
    const dirs: number[] = [-1, 0, 1, 0, -1];
    const dfs = (i: number, j: number) => {
        grid[i][j] = 0;
        for (let k = 0; k < 4; ++k) {
            const x = i + dirs[k];
            const y = j + dirs[k + 1];
            if (x >= 0 && x < m && y >= 0 && y <= n && grid[x][y] === 1) {
                dfs(x, y);
            }
        }
    };
    for (let j = 0; j < n; ++j) {
        for (let i of [0, m - 1]) {
            if (grid[i][j] === 1) {
                dfs(i, j);
            }
        }
    }
    for (let i = 0; i < m; ++i) {
        for (let j of [0, n - 1]) {
            if (grid[i][j] === 1) {
                dfs(i, j);
            }
        }
    }
    return grid.flat().reduce((acc, cur) => acc + cur, 0);
}
