function numberOfWays(n: number): number {
    const mod = 10 ** 9 + 7;
    const f: number[] = Array(n + 1).fill(0);
    f[0] = 1;
    for (const x of [1, 2, 6]) {
        for (let j = x; j <= n; ++j) {
            f[j] = (f[j] + f[j - x]) % mod;
        }
    }
    let ans = f[n];
    if (n >= 4) {
        ans = (ans + f[n - 4]) % mod;
    }
    if (n >= 8) {
        ans = (ans + f[n - 8]) % mod;
    }
    return ans;
}
