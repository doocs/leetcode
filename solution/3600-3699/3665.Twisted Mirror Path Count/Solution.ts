function uniquePaths(grid: number[][]): number {
    const mod = 1e9 + 7;
    const m = grid.length;
    const n = grid[0].length;
    const f: number[][][] = Array.from({ length: m }, () =>
        Array.from({ length: n }, () => [0, 0]),
    );
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            let r = j > 0 ? f[i][j - 1][0] : 0;
            let d = i > 0 ? f[i - 1][j][1] : 0;
            if (i === 0 && j === 0) {
                d = 1;
            }
            if (grid[i][j] === 1) {
                f[i][j] = [d, r];
            } else {
                const s = (r + d) % mod;
                f[i][j] = [s, s];
            }
        }
    }
    return f[m - 1][n - 1][0];
}
