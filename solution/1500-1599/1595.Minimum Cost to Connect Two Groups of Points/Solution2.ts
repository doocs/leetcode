function connectTwoGroups(cost: number[][]): number {
    const m = cost.length;
    const n = cost[0].length;
    const inf = 1 << 30;
    const f: number[] = new Array(1 << n).fill(inf);
    f[0] = 0;
    const g = new Array(1 << n).fill(0);
    for (let i = 1; i <= m; ++i) {
        for (let j = 0; j < 1 << n; ++j) {
            g[j] = inf;
            for (let k = 0; k < n; ++k) {
                if (((j >> k) & 1) === 1) {
                    const c = cost[i - 1][k];
                    g[j] = Math.min(g[j], g[j ^ (1 << k)] + c);
                    g[j] = Math.min(g[j], f[j] + c);
                    g[j] = Math.min(g[j], f[j ^ (1 << k)] + c);
                }
            }
        }
        f.splice(0, f.length, ...g);
    }
    return f[(1 << n) - 1];
}
