function cherryPickup(grid: number[][]): number {
    const m = grid.length;
    const n = grid[0].length;
    const f = Array.from({ length: m }, () =>
        Array.from({ length: n }, () => Array.from({ length: n }, () => -1)),
    );
    f[0][0][n - 1] = grid[0][0] + grid[0][n - 1];
    for (let i = 1; i < m; ++i) {
        for (let j1 = 0; j1 < n; ++j1) {
            for (let j2 = 0; j2 < n; ++j2) {
                const x = grid[i][j1] + (j1 === j2 ? 0 : grid[i][j2]);
                for (let y1 = j1 - 1; y1 <= j1 + 1; ++y1) {
                    for (let y2 = j2 - 1; y2 <= j2 + 1; ++y2) {
                        if (y1 >= 0 && y1 < n && y2 >= 0 && y2 < n && f[i - 1][y1][y2] !== -1) {
                            f[i][j1][j2] = Math.max(f[i][j1][j2], f[i - 1][y1][y2] + x);
                        }
                    }
                }
            }
        }
    }
    return Math.max(...f[m - 1].flat());
}
