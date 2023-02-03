function maxValue(grid: number[][]): number {
    const m = grid.length;
    const n = grid[0].length;
    const f = Array.from({ length: m + 1 }, _ => new Array(n + 1).fill(0));
    for (let i = 1; i <= m; ++i) {
        for (let j = 1; j <= n; ++j) {
            f[i][j] = Math.max(f[i - 1][j], f[i][j - 1]) + grid[i - 1][j - 1];
        }
    }
    return f[m][n];
}
