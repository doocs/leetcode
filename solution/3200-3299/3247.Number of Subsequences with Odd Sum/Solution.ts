function subsequenceCount(nums: number[]): number {
    const mod = 1e9 + 7;
    let f = [0, 0];
    for (const x of nums) {
        const g = [0, 0];
        if (x % 2 === 1) {
            g[0] = (f[0] + f[1]) % mod;
            g[1] = (f[0] + f[1] + 1) % mod;
        } else {
            g[0] = (f[0] + f[0] + 1) % mod;
            g[1] = (f[1] + f[1]) % mod;
        }
        f = g;
    }
    return f[1];
}
