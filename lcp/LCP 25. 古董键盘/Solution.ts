function keyboard(k: number, n: number): number {
    const c: number[][] = Array.from({ length: n + 1 }, () => Array(k + 1).fill(0));
    c[0][0] = 1;
    const mod = 10 ** 9 + 7;
    for (let i = 1; i <= n; ++i) {
        c[i][0] = 1;
        for (let j = 1; j <= k; ++j) {
            c[i][j] = (c[i - 1][j - 1] + c[i - 1][j]) % mod;
        }
    }
    const f: number[][] = Array.from({ length: n + 1 }, () => Array(27).fill(0));
    f[0].fill(1);
    for (let i = 1; i <= n; ++i) {
        for (let j = 1; j < 27; ++j) {
            for (let h = 0; h <= Math.min(i, k); ++h) {
                const v = Number((BigInt(f[i - h][j - 1]) * BigInt(c[i][h])) % BigInt(mod));
                f[i][j] = (f[i][j] + v) % mod;
            }
        }
    }
    return f[n][26];
}
