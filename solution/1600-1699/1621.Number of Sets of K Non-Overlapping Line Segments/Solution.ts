function numberOfSets(n: number, k: number): number {
    const f = Array.from({ length: n + 1 }, _ => new Array(k + 1).fill(0));
    const g = Array.from({ length: n + 1 }, _ => new Array(k + 1).fill(0));
    f[1][0] = 1;
    const mod = 10 ** 9 + 7;
    for (let i = 2; i <= n; ++i) {
        for (let j = 0; j <= k; ++j) {
            f[i][j] = (f[i - 1][j] + g[i - 1][j]) % mod;
            g[i][j] = g[i - 1][j];
            if (j) {
                g[i][j] += f[i - 1][j - 1];
                g[i][j] %= mod;
                g[i][j] += g[i - 1][j - 1];
                g[i][j] %= mod;
            }
        }
    }
    return (f[n][k] + g[n][k]) % mod;
}
