function numRollsToTarget(n: number, k: number, target: number): number {
    const f = Array.from({ length: n + 1 }, () => Array(target + 1).fill(0));
    f[0][0] = 1;
    const mod = 1e9 + 7;
    for (let i = 1; i <= n; ++i) {
        for (let j = 1; j <= Math.min(i * k, target); ++j) {
            for (let h = 1; h <= Math.min(j, k); ++h) {
                f[i][j] = (f[i][j] + f[i - 1][j - h]) % mod;
            }
        }
    }
    return f[n][target];
}
