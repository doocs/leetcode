function rearrangeSticks(n: number, k: number): number {
    const mod = 10 ** 9 + 7;
    const f: number[] = Array(n + 1).fill(0);
    f[0] = 1;
    for (let i = 1; i <= n; ++i) {
        for (let j = k; j; --j) {
            f[j] = (f[j] * (i - 1) + f[j - 1]) % mod;
        }
        f[0] = 0;
    }
    return f[k];
}
