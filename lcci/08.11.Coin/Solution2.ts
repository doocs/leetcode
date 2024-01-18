function waysToChange(n: number): number {
    const mod = 10 ** 9 + 7;
    const coins: number[] = [25, 10, 5, 1];
    const f: number[] = new Array(n + 1).fill(0);
    f[0] = 1;
    for (const c of coins) {
        for (let i = c; i <= n; ++i) {
            f[i] = (f[i] + f[i - c]) % mod;
        }
    }
    return f[n];
}
