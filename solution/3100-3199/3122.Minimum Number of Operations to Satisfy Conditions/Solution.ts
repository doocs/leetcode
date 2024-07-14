function minimumOperations(grid: number[][]): number {
    const m = grid.length;
    const n = grid[0].length;
    const f: number[][] = Array.from({ length: n }, () =>
        Array.from({ length: 10 }, () => Infinity),
    );
    for (let i = 0; i < n; ++i) {
        const cnt: number[] = Array(10).fill(0);
        for (let j = 0; j < m; ++j) {
            cnt[grid[j][i]]++;
        }
        if (i === 0) {
            for (let j = 0; j < 10; ++j) {
                f[i][j] = m - cnt[j];
            }
        } else {
            for (let j = 0; j < 10; ++j) {
                for (let k = 0; k < 10; ++k) {
                    if (j !== k) {
                        f[i][j] = Math.min(f[i][j], f[i - 1][k] + m - cnt[j]);
                    }
                }
            }
        }
    }
    return Math.min(...f[n - 1]);
}
