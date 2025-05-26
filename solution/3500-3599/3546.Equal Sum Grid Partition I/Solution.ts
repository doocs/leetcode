function canPartitionGrid(grid: number[][]): boolean {
    let s = 0;
    for (const row of grid) {
        s += row.reduce((a, b) => a + b, 0);
    }
    if (s % 2 !== 0) {
        return false;
    }
    const [m, n] = [grid.length, grid[0].length];
    let pre = 0;
    for (let i = 0; i < m; ++i) {
        pre += grid[i].reduce((a, b) => a + b, 0);
        if (pre * 2 === s && i + 1 < m) {
            return true;
        }
    }
    pre = 0;
    for (let j = 0; j < n; ++j) {
        for (let i = 0; i < m; ++i) {
            pre += grid[i][j];
        }
        if (pre * 2 === s && j + 1 < n) {
            return true;
        }
    }
    return false;
}
