function uniquePaths(grid: number[][]): number {
    const mod = 1e9 + 7;
    const n = grid[0].length;
    const f: number[][] = Array.from({ length: n + 1 }, () => [0, 0]);
    f[1] = [1, 1];
    for (const row of grid) {
        for (let j = 0; j < n; ++j) {
            const r = f[j][0];
            const d = f[j + 1][1];
            if (row[j] === 1) {
                f[j + 1] = [d, r];
            } else {
                const s = (r + d) % mod;
                f[j + 1] = [s, s];
            }
        }
    }
    return f[n][0];
}
