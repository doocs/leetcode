function numRollsToTarget(n: number, k: number, target: number): number {
    const f = Array(target + 1).fill(0);
    f[0] = 1;
    const mod = 1e9 + 7;
    for (let i = 1; i <= n; ++i) {
        const g = Array(target + 1).fill(0);
        for (let j = 1; j <= Math.min(i * k, target); ++j) {
            for (let h = 1; h <= Math.min(j, k); ++h) {
                g[j] = (g[j] + f[j - h]) % mod;
            }
        }
        f.splice(0, target + 1, ...g);
    }
    return f[target];
}
