function numberOfSets(n: number, k: number): number {
    const mod = 10 ** 9 + 7;
    const f: number[][] = Array.from({ length: n + 1 }, () => Array(k + 1).fill(0));
    const g: number[][] = Array.from({ length: n + 1 }, () => Array(k + 1).fill(0));
    f[1][0] = 1;
    for (let i = 2; i <= n; ++i) {
        for (let j = 0; j <= k; ++j) {
            f[i][j] = (f[i - 1][j] + g[i - 1][j]) % mod;
            g[i][j] = g[i - 1][j];
            if (j) {
                g[i][j] = (g[i][j] + f[i - 1][j - 1]) % mod;
                g[i][j] = (g[i][j] + g[i - 1][j - 1]) % mod;
            }
        }
    }
    return (f[n][k] + g[n][k]) % mod;
}
