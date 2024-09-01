function maxScore(grid: number[][]): number {
    const m = grid.length;
    let mx = 0;
    const g: boolean[][] = Array.from({ length: 101 }, () => Array(m + 1).fill(false));
    for (let i = 0; i < m; ++i) {
        for (const x of grid[i]) {
            g[x][i] = true;
            mx = Math.max(mx, x);
        }
    }
    const f: number[][] = Array.from({ length: mx + 1 }, () => Array(1 << m).fill(0));
    for (let i = 1; i <= mx; ++i) {
        for (let j = 0; j < 1 << m; ++j) {
            f[i][j] = f[i - 1][j];
            for (let k = 0; k < m; ++k) {
                if (g[i][k] && ((j >> k) & 1) === 1) {
                    f[i][j] = Math.max(f[i][j], f[i - 1][j ^ (1 << k)] + i);
                }
            }
        }
    }
    return f[mx][(1 << m) - 1];
}
