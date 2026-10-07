function numberOfWays(corridor: string): number {
    const mod = 10 ** 9 + 7;
    const n = corridor.length;
    const f: number[][] = Array.from({ length: n + 1 }, () => Array(3).fill(0));
    f[n][2] = 1;
    for (let i = n - 1; i >= 0; --i) {
        for (let k = 0; k < 3; ++k) {
            let nk = k + (corridor[i] === 'S' ? 1 : 0);
            if (nk > 2) {
                continue;
            }
            f[i][k] = f[i + 1][nk];
            if (nk === 2) {
                f[i][k] = (f[i][k] + f[i + 1][0]) % mod;
            }
        }
    }
    return f[0][0];
}
