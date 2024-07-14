function satisfiesConditions(grid: number[][]): boolean {
    const [m, n] = [grid.length, grid[0].length];
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            if (i + 1 < m && grid[i][j] !== grid[i + 1][j]) {
                return false;
            }
            if (j + 1 < n && grid[i][j] === grid[i][j + 1]) {
                return false;
            }
        }
    }
    return true;
}
