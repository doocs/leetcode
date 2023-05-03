function numberWays(hats: number[][]): number {
    const n = hats.length;
    const m = Math.max(...hats.flat());
    const g: number[][] = Array.from({ length: m + 1 }, () => []);
    for (let i = 0; i < n; ++i) {
        for (const v of hats[i]) {
            g[v].push(i);
        }
    }
    const f: number[][] = Array.from({ length: m + 1 }, () =>
        Array.from({ length: 1 << n }, () => 0),
    );
    f[0][0] = 1;
    const mod = 1e9 + 7;
    for (let i = 1; i <= m; ++i) {
        for (let j = 0; j < 1 << n; ++j) {
            f[i][j] = f[i - 1][j];
            for (const k of g[i]) {
                if (((j >> k) & 1) === 1) {
                    f[i][j] = (f[i][j] + f[i - 1][j ^ (1 << k)]) % mod;
                }
            }
        }
    }
    return f[m][(1 << n) - 1];
}
