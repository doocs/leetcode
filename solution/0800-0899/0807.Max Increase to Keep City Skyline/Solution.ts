function maxIncreaseKeepingSkyline(grid: number[][]): number {
    let rows = grid.map(arr => Math.max(...arr)),
        cols = [];
    let m = grid.length,
        n = grid[0].length;
    for (let j = 0; j < n; ++j) {
        cols[j] = grid[0][j];
        for (let i = 1; i < m; ++i) {
            cols[j] = Math.max(cols[j], grid[i][j]);
        }
    }

    let ans = 0;
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            ans += Math.min(rows[i], cols[j]) - grid[i][j];
        }
    }
    return ans;
}
