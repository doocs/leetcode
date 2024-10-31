function countOfArrays(n: number, m: number, k: number): number {
    const mod = 1e9 + 7;
    const cnt0 = Math.floor(m / 2);
    const cnt1 = m - cnt0;
    const f: number[][] = Array.from({ length: k + 1 }, () => [0, 0]);
    f[0][1] = 1;
    for (let i = 0; i < n; i++) {
        const g: number[][] = Array.from({ length: k + 1 }, () => [0, 0]);
        for (let j = 0; j <= k; j++) {
            g[j][0] = ((cnt0 * (f[j][1] + (j > 0 ? f[j - 1][0] : 0))) % mod) % mod;
            g[j][1] = ((cnt1 * (f[j][0] + f[j][1])) % mod) % mod;
        }
        f.splice(0, f.length, ...g);
    }
    return (f[k][0] + f[k][1]) % mod;
}
