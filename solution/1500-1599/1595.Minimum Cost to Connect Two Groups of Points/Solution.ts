function connectTwoGroups(cost: number[][]): number {
    const m = cost.length;
    const n = cost[0].length;
    const inf = 1 << 30;
    const f: number[][] = Array(m + 1)
        .fill(0)
        .map(() => Array(1 << n).fill(inf));
    f[0][0] = 0;
    for (let i = 1; i <= m; ++i) {
        for (let j = 0; j < 1 << n; ++j) {
            for (let k = 0; k < n; ++k) {
                if (((j >> k) & 1) === 1) {
                    const c = cost[i - 1][k];
                    f[i][j] = Math.min(f[i][j], f[i][j ^ (1 << k)] + c);
                    f[i][j] = Math.min(f[i][j], f[i - 1][j] + c);
                    f[i][j] = Math.min(f[i][j], f[i - 1][j ^ (1 << k)] + c);
                }
            }
        }
    }
    return f[m][(1 << n) - 1];
}
