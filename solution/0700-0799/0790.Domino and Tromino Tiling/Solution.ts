function numTilings(n: number): number {
    const mod = 1_000_000_007;
    let f: number[] = [1, 0, 0, 0];

    for (let i = 1; i <= n; ++i) {
        const g: number[] = Array(4);
        g[0] = (f[0] + f[1] + f[2] + f[3]) % mod;
        g[1] = (f[2] + f[3]) % mod;
        g[2] = (f[1] + f[3]) % mod;
        g[3] = f[0] % mod;
        f = g;
    }

    return f[0];
}
