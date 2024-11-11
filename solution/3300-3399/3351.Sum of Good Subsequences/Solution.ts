function sumOfGoodSubsequences(nums: number[]): number {
    const mod = 10 ** 9 + 7;
    const mx = Math.max(...nums);
    const f: number[] = Array(mx + 1).fill(0);
    const g: number[] = Array(mx + 1).fill(0);
    for (const x of nums) {
        f[x] += x;
        g[x] += 1;
        if (x > 0) {
            f[x] = (f[x] + f[x - 1] + ((g[x - 1] * x) % mod)) % mod;
            g[x] = (g[x] + g[x - 1]) % mod;
        }
        if (x + 1 <= mx) {
            f[x] = (f[x] + f[x + 1] + ((g[x + 1] * x) % mod)) % mod;
            g[x] = (g[x] + g[x + 1]) % mod;
        }
    }
    return f.reduce((acc, cur) => (acc + cur) % mod, 0);
}
