function waysToReachTarget(target: number, types: number[][]): number {
    const n = types.length;
    const mod = 10 ** 9 + 7;
    const f: number[][] = Array.from({ length: n + 1 }, () =>
        Array(target + 1).fill(0),
    );
    f[0][0] = 1;
    for (let i = 1; i <= n; ++i) {
        const [count, marks] = types[i - 1];
        for (let j = 0; j <= target; ++j) {
            for (let k = 0; k <= count; ++k) {
                if (j >= k * marks) {
                    f[i][j] = (f[i][j] + f[i - 1][j - k * marks]) % mod;
                }
            }
        }
    }
    return f[n][target];
}
