function countHousePlacements(n: number): number {
    const f = new Array(n);
    const g = new Array(n);
    f[0] = g[0] = 1n;
    const mod = BigInt(10 ** 9 + 7);
    for (let i = 1; i < n; ++i) {
        f[i] = g[i - 1];
        g[i] = (f[i - 1] + g[i - 1]) % mod;
    }
    const v = f[n - 1] + g[n - 1];
    return Number(v ** 2n % mod);
}
