function maxValue(grid: number[][]): number {
    let n = grid.length;
    let m = grid[0].length;
    for (let i = 1; i < n; i++) {
        grid[i][0] += grid[i - 1][0];
    }
    for (let i = 1; i < m; i++) {
        grid[0][i] += grid[0][i - 1];
    }
    for (let i = 1; i < n; i++) {
        for (let j = 1; j < m; j++) {
            grid[i][j] += Math.max(grid[i][j - 1], grid[i - 1][j]);
        }
    }
    return grid[n - 1][m - 1];
}
