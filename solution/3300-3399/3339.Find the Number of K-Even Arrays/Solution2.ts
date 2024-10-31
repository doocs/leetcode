function countOfArrays(n: number, m: number, k: number): number {
    const f: number[][][] = Array.from({ length: n + 1 }, () =>
        Array.from({ length: k + 1 }, () => Array(2).fill(0)),
    );
    f[0][0][1] = 1;
    const mod = 1e9 + 7;
    const cnt0 = Math.floor(m / 2);
    const cnt1 = m - cnt0;
    for (let i = 1; i <= n; ++i) {
        for (let j = 0; j <= k; ++j) {
            f[i][j][0] = (cnt0 * (f[i - 1][j][1] + (j ? f[i - 1][j - 1][0] : 0))) % mod;
            f[i][j][1] = (cnt1 * (f[i - 1][j][0] + f[i - 1][j][1])) % mod;
        }
    }
    return (f[n][k][0] + f[n][k][1]) % mod;
}
