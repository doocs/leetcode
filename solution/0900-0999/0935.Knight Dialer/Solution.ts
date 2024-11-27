function knightDialer(n: number): number {
    const mod = 1e9 + 7;
    const f: number[] = Array(10).fill(1);
    while (--n) {
        const g: number[] = Array(10).fill(0);
        g[0] = (f[4] + f[6]) % mod;
        g[1] = (f[6] + f[8]) % mod;
        g[2] = (f[7] + f[9]) % mod;
        g[3] = (f[4] + f[8]) % mod;
        g[4] = (f[0] + f[3] + f[9]) % mod;
        g[6] = (f[0] + f[1] + f[7]) % mod;
        g[7] = (f[2] + f[6]) % mod;
        g[8] = (f[1] + f[3]) % mod;
        g[9] = (f[2] + f[4]) % mod;
        f.splice(0, 10, ...g);
    }
    return f.reduce((a, b) => (a + b) % mod);
}
