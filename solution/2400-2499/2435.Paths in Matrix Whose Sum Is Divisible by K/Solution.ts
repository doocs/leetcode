function numberOfPaths(grid: number[][], K: number): number {
    const mod = 1e9 + 7;
    const m = grid.length;
    const n = grid[0].length;
    const f: number[][][] = Array.from({ length: m }, () =>
        Array.from({ length: n }, () => Array(K).fill(0)),
    );
    f[0][0][grid[0][0] % K] = 1;
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            for (let k = 0; k < K; ++k) {
                const k0 = (k - (grid[i][j] % K) + K) % K;
                if (i > 0) {
                    f[i][j][k] = (f[i][j][k] + f[i - 1][j][k0]) % mod;
                }
                if (j > 0) {
                    f[i][j][k] = (f[i][j][k] + f[i][j - 1][k0]) % mod;
                }
            }
        }
    }
    return f[m - 1][n - 1][0];
}
